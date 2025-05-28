package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMixMapper;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixProdStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionStoreMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductMixIngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.RecentActivityService;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionMixService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductionMixImpl implements ProductionMixService {
    private final ProductMixRepository productMixRepository;
    private final ProductMixMapper productMixMapper;
    private final ProductRepository productRepository;
    private final ProductionRepository productionRepository;
    private final RecentActivityService recentActivityService;
    private final IngredientRepository ingredientRepository;
    private final ProductMixIngredientRepository productMixIngredientRepository;
    private final ProductionStoreRepository productionStoreRepository;
    private final ProductionStoreMapper productionStoreMapper;

    public ProductionMixImpl(ProductMixRepository productMixRepository, ProductMixMapper productMixMapper, ProductRepository productRepository, ProductionRepository productionRepository, RecentActivityService recentActivityService, IngredientRepository ingredientRepository, ProductMixIngredientRepository productMixIngredientRepository, ProductionStoreRepository productionStoreRepository,
                             ProductionStoreMapper productionStoreMapper) {
        this.productMixRepository = productMixRepository;
        this.productMixMapper = productMixMapper;
        this.productRepository = productRepository;
        this.productionRepository = productionRepository;
        this.recentActivityService = recentActivityService;
        this.ingredientRepository = ingredientRepository;
        this.productMixIngredientRepository = productMixIngredientRepository;
        this.productionStoreRepository = productionStoreRepository;
        this.productionStoreMapper = productionStoreMapper;
    }

    @Override
    @Transactional
    public ProductMixProdStoreDto addProductMix(ProductMixDto pmxdto, long prID) {
        System.out.println(pmxdto.toString());
        // Convert DTO to entity
        ProductMix productMix = productMixMapper.toEntity(pmxdto);
        

        // Load required entities to ensure they are managed
        ProductionStore ps = productionStoreRepository.findProductionStoreByProduction_Id(prID)
                .orElseThrow(() -> new EntityException("Production Store not found", HttpStatus.NOT_FOUND));

        Product product = productRepository.findById(pmxdto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMix.setProduct(product);
        productMix.setProduction(ps.getProduction());
        Set<Long> ingredientIds = pmxdto.getProductMixIngredients().stream().map(ProductMixIngredientDto::getIngredientId).collect(Collectors.toSet());
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);
        System.out.println(ingredients);
        productMix.getProductMixIngredients().clear();
        for (ProductMixIngredientDto productMixIngredientDto : pmxdto.getProductMixIngredients()) {
            for (Ingredient ingredient : ingredients) {
                if(Objects.equals(ingredient.getId(), productMixIngredientDto.getIngredientId())) {
                    ProductMixIngredient productMixIngredient = new ProductMixIngredient();
                    productMixIngredient.setIngredient(ingredient);
                    productMixIngredient.setLitresUsed(productMixIngredientDto.getLitresUsed());
                    productMix.getProductMixIngredients().add(productMixIngredient);
                }
            }
        }

        // Allocate ingredient usage and calculate total liters used
        allocateIngredientUsage(productMix.getProductMixIngredients(), ps.getIngredientStores());



        productMix.setTotalLitersUsed(calculateSum(productMix));

        // Save the updated ProductionStore if ingredient stores were modified
        ProductionStore savedStore = productionStoreRepository.save(ps);

        // Save ProductMix — cascade will handle LitersUsedForIngredient
        ProductMix savedProductMix = productMixRepository.save(productMix);

        ProductMixProdStoreDto mixProdStoreDto = new ProductMixProdStoreDto();
        mixProdStoreDto.setProductMix(productMixMapper.toDto(savedProductMix));
        mixProdStoreDto.setProductionStore(productionStoreMapper.toDto(savedStore));

        return mixProdStoreDto;
    }


    private double calculateSum(ProductMix productMix) {
        return productMix.getProductMixIngredients().stream()
                .mapToDouble(com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient::getLitresUsed)
                .sum();
    }

    /*
    *
    *  double sum = pmx.getLitersUsedForIngredients().stream()
                .mapToDouble(LitersUsedForIngredient::getLitresUsed)
                .sum();
        pmx.setTotalLitersUsed(sum);*/

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public ProductMixDto updateThisProductMix(ProductMixDto productMixDto, long productMixId) {

        ProductMix productMix = productMixMapper.toEntity(productMixDto);

        ProductMix retreivedProductMix = productMixRepository.findProductMixById(productMixId).orElseThrow(() ->
                new EntityException("Product mix not found", HttpStatus.NOT_FOUND));

        retreivedProductMix.setQty(productMix.getQty());
        retreivedProductMix.setBrixOnDiluent(productMix.getBrixOnDiluent());
        retreivedProductMix.setInitialBrix(productMix.getInitialBrix());
        retreivedProductMix.setFinalBrix(productMix.getFinalBrix());
        retreivedProductMix.setTotalLitersUsed(productMix.getTotalLitersUsed());
        retreivedProductMix.setInitialPH(productMix.getInitialPH());
        retreivedProductMix.setFinalPH(productMix.getFinalPH());
        retreivedProductMix.getProductMixIngredients().clear();
        retreivedProductMix.getProductMixIngredients().addAll(productMix.getProductMixIngredients());

        if (!Objects.equals(productMix.getProduct().getId(), retreivedProductMix.getProduct().getId())) {
            // Load managed Product entity
            Product product = productRepository.findById(productMix.getProduct().getId())
                    .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));
            retreivedProductMix.setProduct(product);

            ProductMix prmx = productMixRepository.save(retreivedProductMix);
            product.addProductMix(prmx);
            productRepository.save(product);
            return productMixMapper.toDto(prmx);
        }


        ProductMix prmx = productMixRepository.save(retreivedProductMix);

        // Log the recent activity
        recentActivityService.addActivity(
                "Production",
                "Production Mix  " + prmx.getId() + " in production " + prmx.getProduction().getName() + " updated at" + LocalDateTime.now()
        );
        return productMixMapper.toDto(prmx);
    }

    @Override
    public ProductMixDto findThisProductMix(long id) {
        return productMixMapper.toDto(productMixRepository.findProductMixById(id).orElseThrow(() -> new EntityException("Product Mix not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<ProductMixDto> findAllProductMix() {
        return List.of();
    }

    @Override
    public Page<ProductMixDto> findAllProductMix(Pageable pageable) {
        return productMixRepository.findAll(pageable)
                .map(productMixMapper::toDto);
    }

    @Override
    public Page<ProductMixDto> findProductMixesBySearch(String search, Pageable pageable) {
        Specification<ProductMix> spec = (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No filter if search is empty
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("production").get("name")), // Assuming 'name' is the field in 'Production'
                    "%" + search.toLowerCase() + "%"
            );
        };
        return productMixRepository.findAll(spec, pageable).map(productMixMapper::toDto);
    }


    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void deleteThisProductMix(long productMixId) {
        ProductMix retreivedProductMix = productMixRepository.findProductMixById(productMixId).orElseThrow(() ->
                new EntityException("Product mix not found", HttpStatus.NOT_FOUND));

        // Load required entities to ensure they are managed
        ProductionStore ps = productionStoreRepository.findProductionStoreByProduction_Id(retreivedProductMix.getProduction().getId())
                .orElseThrow(() -> new EntityException("Production Store not found", HttpStatus.NOT_FOUND));

        // deallocate ingredient usage
        onDeleteDeallocateIngredientUsageFromProductMix(retreivedProductMix.getProductMixIngredients(), ps.getIngredientStores());
        // Save the updated ProductionStore if ingredient stores were modified
        productionStoreRepository.save(ps);
        productMixRepository.delete(retreivedProductMix);
    }


    private Production getProduction(long id) {
        return productionRepository.findProductionById(id)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
    }

    private Product getProduct(long id) {
        return productRepository.findById(id).orElse(null);
    }

    private List<Purchase> getPurchaseEntries(Production production) {
        return production.getPurchaseEntries();
    }


    private void allocateIngredientUsage(List<ProductMixIngredient> usages, List<IngredientStore> store) {
        for (ProductMixIngredient used : usages) {
            double amountToUse = used.getLitresUsed();
            boolean found = false;

            for (IngredientStore ing : store) {
                if (ing.getIngredient().getId().equals(used.getIngredient().getId())) {
                    found = true;

                    if (amountToUse > ing.getUsableLitresLeft()) {
                        throw new IllegalArgumentException("Not enough usable liters for ingredient: "
                                + ing.getIngredient().getName() + ". Requested: "
                                + amountToUse + ", Available: " + ing.getUsableLitresLeft());
                    }

                    double remaining = ing.getUsableLitresLeft() - amountToUse;
                    ing.setUsableLitresLeft(remaining);
                    break;
                }
            }

            if (!found) {
                throw new IllegalArgumentException("Ingredient with ID " + used.getIngredient().getId() + " not found in store.");
            }
        }
    }


    private void onDeleteDeallocateIngredientUsageFromProductMix(List<ProductMixIngredient> usages, List<IngredientStore> store) {
        for (ProductMixIngredient used : usages) {
            double amountToUse = used.getLitresUsed();
            boolean found = false;

            for (IngredientStore ing : store) {
                if (ing.getIngredient().getId().equals(used.getIngredient().getId())) {
                    found = true;
                    double putBackToUsableLitersLeft = ing.getUsableLitresLeft() + amountToUse;
                    ing.setUsableLitresLeft(putBackToUsableLitersLeft);
                    // Update the LitersUsedForIngredient entity to reflect the deallocation
                    used.setLitresUsed(0.0);
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Ingredient with ID " + used.getIngredient().getId() + " not found in store.");
            }
        }
    }
}
