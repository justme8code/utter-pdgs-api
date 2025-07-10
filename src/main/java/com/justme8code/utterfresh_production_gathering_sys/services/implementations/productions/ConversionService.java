package com.justme8code.utterfresh_production_gathering_sys.services.implementations.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionFullDetail;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionFullDetailWithBatchInfo;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionPayload;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ConversionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionBatchMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionStoreMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.PurchaseMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.event.*;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import com.justme8code.utterfresh_production_gathering_sys.repository.*;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.ProductionBatchHelper;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.ProductionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionService {

    private final ConversionMapper conversionMapper;
    private final PurchaseRepository purchaseRepository;

    private final ConversionFieldRepository mTIField;

    private final ProductionStoreRepository productionStoreRepository;
    private final ProductionRepository productionRepository;

    private final PurchaseMapper purchaseMapper;
    private final ConversionRepository conversionRepository;
    private final ProductionStoreMapper productionStoreMapper;
    private final ProductionBatchRepository productionBatchRepository;
    private final ProductionBatchMapper productionBatchMapper;

    @Autowired
    public ConversionService(ConversionMapper conversionMapper,
                             PurchaseRepository purchaseRepository,
                             PurchaseMapper purchaseMapper, IngredientRepository ingredientRepository, ConversionFieldRepository mTIField, ProductionStoreRepository productionStoreRepository, ProductionRepository productionRepository, ConversionRepository conversionRepository,
                             ProductionStoreMapper productionStoreMapper, ProductionBatchRepository productionBatchRepository,
                             ProductionBatchMapper productionBatchMapper) {
        this.conversionMapper = conversionMapper;
        this.purchaseRepository = purchaseRepository;

        this.purchaseMapper = purchaseMapper;

        this.mTIField = mTIField;

        this.productionStoreRepository = productionStoreRepository;
        this.productionRepository = productionRepository;
        this.conversionRepository = conversionRepository;
        this.productionStoreMapper = productionStoreMapper;
        this.productionBatchRepository = productionBatchRepository;
        this.productionBatchMapper = productionBatchMapper;
    }


    private Purchase verifyBeforeCreation(ConversionPayload dto, long purchaseId, long productionId) {
        // set mti usage initially.
        Purchase purchase = purchaseRepository.findPurchaseById(purchaseId)
                .orElseThrow(() -> new EntityException("Purchase Entry not found", HttpStatus.NOT_FOUND));

        if (purchase.getProduction().getId() != productionId) {
            throw new EntityException("Purchase Entry does not belong to production", HttpStatus.BAD_REQUEST);
        }

        return purchase;
    }


    private Production getProduction(long id) {
        return productionRepository.findProductionById(id)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
    }

    /**
     * Since adding a conversion affects a purchase Usage
     * And also which affects the Production store(RawMaterial Store)
     * Here we need to first temporally remove the data of purchase usage
     * linked to Raw material store in the ProductionStore(RawMaterial Store)
     * then the actual update is done for purchase usage when removeFromPEUsage is called
     * After that, we can now update our Raw Material store again by calling the createOrAddToRawMaterialStore
     * This is because normally Purchase Usage already tracks it purchase,
     * but now we are using RawMaterialStore to kind of have a universal view of all purchases usages
     * which requires us to create add and remove, adding and creating would be easy but removing might be hard,
     * The reason why removing might be hard, is because when creating a conversion, the whole resource
     * of a purchase usage isn't used for that conversion so there might still be left resource, but
     * our universal raw material store wouldn't be able to track it, because it only tracks initials meaning in
     * a sense the first data of a Purchase Usage. so which means that if we need to remove from our universal
     * RawMaterial Store, we need to remove the initial same as previous data of purchase usage from RawMaterial Store,
     * right which the tempRemoverFromRawMaterialStore does
     * then when purchase usage it self is now updated we now update our RawMaterial Store with this new update
     * which now becomes our new Initial so that is how it will continue to track the universal usage which is
     * RawMaterial Store.
     **/


    @Transactional
    public ConversionFullDetailWithBatchInfo createMaterialToIngredient(ConversionPayload dto, long purchaseId, long productionId) {
        Purchase purchase = verifyBeforeCreation(dto, purchaseId, productionId);
        Production production = getProduction(productionId);
        List<Ingredient> peIngredients = purchase.getRawMaterial().getIngredients();
        ProductionBatch productionBatch = ProductionBatchHelper.getOrCreateActiveBatch(production,productionBatchRepository);

        // 1. Create the main transient Conversion object
        Conversion conversion = conversionMapper.toEntity(dto);

        // 2. Set its relationships to OTHER PERSISTENT objects
        conversion.setProduction(production);
        conversion.setPurchase(purchase); // The back-reference is important!
        conversion.setBatch(productionBatch);

        // 3. Create the transient ConversionField children and link them to the transient Conversion
        List<IngredientStore> ingredientStores = production.getProductionStore().getIngredientStores();
        int index = 0;

        // IMPORTANT: Clear the list from the mapper if it's not empty,
        // as we are manually building it now with the correct relationships.
        List<ConversionField> fieldsFromDto = new ArrayList<>(conversion.getFields());
        conversion.getFields().clear();

        for (ConversionField conversionField : fieldsFromDto) {

            double usableLitres = conversionField.getOutPutLitres() - conversionField.getProductionLitresLost();
            conversionField.setUsableLitres(usableLitres);

            Ingredient ingredient = peIngredients.get(index);
            conversionField.setIngredient(ingredient);

            // Use the helper method to link the child to the parent (Conversion)
            conversion.getFields().add(conversionField); // This sets both sides of the link!

            // Update IngredientStore's usableLitresLeft...
            for (IngredientStore store : ingredientStores) {
                if (store.getIngredient().getId().equals(ingredient.getId())) {
                    double updatedLitres = store.getUsableLitresLeft() + usableLitres;
                    store.setUsableLitresLeft(updatedLitres);
                    break;
                }
            }
            index++;
        }

        // 4. Add the fully-formed (but still transient) Conversion to the Purchase's collection
        purchase.getConversions().add(conversion);

        // Perform other logic
        removeFromPEUsage(purchase.getPurchaseUsage());
        ProductionHelper.createOrAddToRawMaterialStore(purchase, production);

        // 5. Save the PARENT object. Cascading will handle saving the new Conversion AND its new ConversionFields.
        Purchase updatedPurchase = purchaseRepository.save(purchase);

        // ... build and return your DTO ...
        PurchaseDto purchaseDto = purchaseMapper.toDto(updatedPurchase);
        ProductionStore productionStore = purchase.getProduction().getProductionStore();
        ConversionFullDetail conversionFullDetail = new ConversionFullDetail();
        conversionFullDetail.setPurchase(purchaseDto);
        // Get the newly saved conversion, which is the last one in the list
        ConversionDto conversionDto = conversionMapper.toDto(updatedPurchase.getConversions().getLast());
        conversionFullDetail.setConversion(conversionDto);
        conversionFullDetail.setProductionStore(productionStoreMapper.toDto(productionStore));

        return new ConversionFullDetailWithBatchInfo(productionBatchMapper.toDto(productionBatch),conversionFullDetail);
    }

    // fetch list of material to ingredient by purchase entry id
    public List<ConversionDto> getMaterialToIngredientsByPurchaseEntryId(Long purchaseEntryId, Long productionId) {
        Purchase purchase = purchaseRepository.findById(purchaseEntryId)
                .orElseThrow(() -> new EntityException("Purchase Entry not found", HttpStatus.NOT_FOUND));
        if (!purchase.getProduction().getId().equals(productionId)) {
            throw new EntityException("Purchase Entry does not belong to production", HttpStatus.BAD_REQUEST);
        }
        return purchase.getConversions().stream()
                .map(conversionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Update PurchaseEntry usage
    private void removeFromPEUsage(PurchaseUsage purchaseUsage) {
        double totalKgUsed = purchaseUsage.getPurchase().getConversions().stream()
                .flatMap(conversion -> conversion.getFields().stream())
                .mapToDouble(ConversionField::getKgUsed)
                .sum();
        purchaseUsage.setUsableWeightLeft(purchaseUsage.getPurchase().getUsableWeight() - totalKgUsed);
        purchaseUsage.setTotalKgUsed(totalKgUsed);
    }

    // delete material to ingredient by purchase entry id and material to ingredient id
    @Transactional
    public PurchaseDto deleteMaterialToIngredient(Long purchaseEntryId, Long materialToIngredientId) {
        Purchase purchase = purchaseRepository
                .findPurchaseById(purchaseEntryId)
                .orElseThrow(() -> new EntityException("Purchase Entry not found", HttpStatus.NOT_FOUND));

        purchase.getConversions()
                .removeIf(materialToIngredient -> materialToIngredient.getId().equals(materialToIngredientId));
        // Update the PEUsage
        return purchaseMapper.toDto(purchaseRepository.save(purchase));
    }
}
