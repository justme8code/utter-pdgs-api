package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.dtos.*;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ConversionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionStoreMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.PurchaseMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.*;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.PurchaseHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional

public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;
    private final ProductionRepository productionRepository;
    private final SupplierRepository supplierRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductionStoreRepository productionStoreRepository;
    private final ConversionMapper conversionMapper;
    private final ProductionMapper productionMapper;
    private final ProductionStoreMapper productionStoreMapper;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper, ProductionRepository productionRepository, SupplierRepository supplierRepository, RawMaterialRepository rawMaterialRepository, ProductionStoreRepository productionStoreRepository,
                           ConversionMapper conversionMapper,
                           ProductionMapper productionMapper,
                           ProductionStoreMapper productionStoreMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.productionRepository = productionRepository;
        this.supplierRepository = supplierRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.productionStoreRepository = productionStoreRepository;
        this.conversionMapper = conversionMapper;
        this.productionMapper = productionMapper;
        this.productionStoreMapper = productionStoreMapper;
    }

    public PurchasePRStoreDto createPurchaseEntry(Long productionId, PurchaseDto purchaseDto) {
        // Checks if it's null
        if (Objects.isNull(purchaseDto)) {
            throw new EntityException("Purchase entry cannot be null", HttpStatus.BAD_REQUEST);
        }
        // Checks if production exists
        Production production = productionRepository.findProductionById(productionId)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
        // Checks if raw material exists
        RawMaterial rm = rawMaterialRepository.findById(purchaseDto.getRawMaterial().getId())
                .orElseThrow(() -> new EntityException("Raw material not found", HttpStatus.NOT_FOUND));

        // Creates a new purchase usage to track the purchase
        PurchaseUsage purchaseUsage = new PurchaseUsage();
        purchaseUsage.setUsableWeightLeft(purchaseDto.getUsableWeight());// sets initial data
        // converts to entity purchase from dto
        Purchase purchase = purchaseMapper.toEntity(purchaseDto);
        purchase.setPurchaseUsage(purchaseUsage);
        // set which purchase the purchase usage is associated to
        purchaseUsage.setPurchase(purchase);
        // sets purchase production
        purchase.setProduction(production);

        if(rm.getIngredients() == null || rm.getIngredients().isEmpty()) {
            throw new EntityException("Can't create purchase, because No ingredients found for "+ rm.getName(), HttpStatus.BAD_REQUEST);
        }
        PurchaseHelper.checkIfIngredientStoreExistOrCreateIngredientStore(purchase, production,rm);
        PurchaseHelper.checkIfPurchaseRawMaterialExistsOrCreateRawMaterialStore(purchase,production.getProductionStore());

        ProductionStore ps = productionStoreRepository.save(production.getProductionStore());
        Purchase savedPurchase = purchaseRepository.save(purchase); // save purchase

        PurchasePRStoreDto purchasePRStoreDto = new PurchasePRStoreDto();
        purchasePRStoreDto.setPurchase(purchaseMapper.toDto(savedPurchase));
        purchasePRStoreDto.setProductionStore(productionStoreMapper.toDto(ps));

        return purchasePRStoreDto;
    }

 

    // get list of purchases entries by a production where deleted is not true
    public List<PurchaseDto> getNonDeletedPurchaseEntriesByProductionId(Long productionId) {
        return purchaseRepository.findAllByProductionIdAndDeletedFalse(productionId)
                .stream()
                .map(purchaseMapper::toDto)
                .toList();
    }

    public PurchaseDto updatePurchaseEntry(Long productionId,Long purchaseId, PurchaseDto dto) {

        // Check if purchase exist
        Purchase purchase = purchaseRepository.findPurchaseById(purchaseId)
                .orElseThrow(() -> new EntityException("Purchase not found", HttpStatus.NOT_FOUND));

        if (!Objects.equals(purchase.getProduction().getId(), productionId)){
            throw new EntityException("Purchase entry does not belong to production", HttpStatus.BAD_REQUEST);
        }

        // Purchase shouldn't be updated if it already has a conversion, because it would or definitely
        // cause data inconsistency.
        if(!purchase.getConversions().isEmpty()){
            throw new EntityException("Purchase already used for conversion", HttpStatus.BAD_REQUEST);
        }

        // reset this to the updated value
        purchase.getPurchaseUsage().setUsableWeightLeft(dto.getUsableWeight());

        // update supplier if ever changed
        if(purchase.getSupplier() != null && !Objects.equals(purchase.getSupplier().getId(), dto.getSupplier().getId())){
            Supplier s = supplierRepository.findById(dto.getSupplier().getId())
                    .orElseThrow(() -> new EntityException("Supplier not found", HttpStatus.NOT_FOUND));
            purchase.setSupplier(s);
        }

        // update raw material if it ever changed
        if(purchase.getRawMaterial() != null && !Objects.equals(purchase.getRawMaterial().getId(), dto.getRawMaterial().getId())){
            RawMaterial r = rawMaterialRepository.findById(dto.getRawMaterial().getId())
                    .orElseThrow(() -> new EntityException("Raw material not found", HttpStatus.NOT_FOUND));
            purchase.setRawMaterial(r);
        }

        // do a partial update
        purchaseMapper.partialUpdate(dto, purchase);
        // update purchase and get it back
        Purchase updatedEntry = purchaseRepository.save(purchase);
        return purchaseMapper.toDto(updatedEntry);// returns the purchase with it's updated values.
    }


    @Transactional
    public void deletePurchaseEntry(Long productionId, Long purchaseEntryId) {
        // Get purchase or else throw an error
        Purchase purchase = purchaseRepository.findPurchaseById(purchaseEntryId)
                .orElseThrow(() -> new EntityException("Purchase not found", HttpStatus.NOT_FOUND));

        // Checks if purchase belongs to a production
        if(!purchase.getProduction().getId().equals(productionId)){
            throw new EntityException("Purchase entry does not belong to production", HttpStatus.BAD_REQUEST);
        }
        // delete purchase
        purchaseRepository.delete(purchase);
    }



}
