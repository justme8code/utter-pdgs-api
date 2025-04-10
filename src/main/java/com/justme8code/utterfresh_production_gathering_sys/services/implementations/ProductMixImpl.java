package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMixMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.*;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductMixService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductMixImpl implements ProductMixService {
    private final ProductMixRepository productMixRepository;
    private final ProductMixMapper productMixMapper;
    private final ProductRepository productRepository;
    private final ProductionRepository productionRepository;
    private final VariantRepository variantRepository;
    private final IngredientUsageRepository ingredientUsageRepository;

    public ProductMixImpl(ProductMixRepository productMixRepository, ProductMixMapper productMixMapper, ProductRepository productRepository, ProductionRepository productionRepository,
                          VariantRepository variantRepository, IngredientUsageRepository ingredientUsageRepository) {
        this.productMixRepository = productMixRepository;
        this.productMixMapper = productMixMapper;
        this.productRepository = productRepository;
        this.productionRepository = productionRepository;
        this.variantRepository = variantRepository;
        this.ingredientUsageRepository = ingredientUsageRepository;
    }

    @Override
    public ProductMixDto createANewProductMix(ProductMixDto productMixDto) {
        ProductMix productMix = productMixMapper.toEntity(productMixDto);

        Production production = productionRepository.findProductionById(productMixDto.getProductionId())
                .orElseThrow(() -> new EntityException("You can't make a production mix without being in a production", HttpStatus.FORBIDDEN));

        // Load managed Product entity
        Product product = productRepository.findById(productMix.getProduct().getId())
                .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));

        productMix.setProduction(production);
        productMix.setProduct(product);

        // Save the product mix FIRST so it becomes a managed entity
        ProductMix savedMix = productMixRepository.save(productMix);

        // Set the managed ProductMix to each IngredientUsage (to fix transient error)
        productMix.getIngredientUsages().forEach(usage -> usage.setProductMix(savedMix));

        // Save the ingredient usages
        List<IngredientUsage> savedUsages = ingredientUsageRepository.saveAll(productMix.getIngredientUsages());

        savedMix.getIngredientUsages().clear();
        savedMix.getIngredientUsages().addAll(savedUsages);

        // Update Product with the new mix
        product.addProductMix(savedMix);
        productRepository.save(product);

        return productMixMapper.toDto(savedMix);
    }


    @Override
    public ProductMixDto updateThisProductMix(ProductMixDto productMixDto,long productMixId) {

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

        productMix.getIngredientUsages().forEach(usage -> usage.setProductMix(retreivedProductMix));
        retreivedProductMix.getIngredientUsages().clear();
        retreivedProductMix.getIngredientUsages().addAll(productMix.getIngredientUsages());

        if(!Objects.equals(productMix.getProduct().getId(), retreivedProductMix.getProduct().getId())) {
            // Load managed Product entity
            Product product = productRepository.findById(productMix.getProduct().getId())
                    .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));
            retreivedProductMix.setProduct(product);

            ProductMix prmx =productMixRepository.save(retreivedProductMix);
            product.addProductMix(prmx);
            productRepository.save(product);
            return productMixMapper.toDto(prmx);
        }


        ProductMix prmx = productMixRepository.save(retreivedProductMix);

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
    @Transactional
    public void deleteThisProductMix(long productMixId) {
        ProductMix retreivedProductMix = productMixRepository.findProductMixById(productMixId).orElseThrow(() ->
                new EntityException("Product mix not found", HttpStatus.NOT_FOUND));
        productMixRepository.delete(retreivedProductMix);
    }

}
