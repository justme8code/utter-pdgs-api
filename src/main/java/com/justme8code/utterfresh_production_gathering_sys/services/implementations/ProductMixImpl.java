package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMixMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductMixRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.VariantRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductMixService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMixImpl implements ProductMixService {
    private final ProductMixRepository productMixRepository;
    private final ProductMixMapper productMixMapper;
    private final ProductRepository productRepository;
    private final ProductionRepository productionRepository;
    private final VariantRepository variantRepository;

    public ProductMixImpl(ProductMixRepository productMixRepository, ProductMixMapper productMixMapper, ProductRepository productRepository, ProductionRepository productionRepository, VariantRepository variantRepository) {
        this.productMixRepository = productMixRepository;
        this.productMixMapper = productMixMapper;
        this.productRepository = productRepository;
        this.productionRepository = productionRepository;
        this.variantRepository = variantRepository;
    }

    @Override
    public ProductMixDto createANewProductMix(ProductMixDto productMixDto) {
        ProductMix productMix = productMixMapper.toEntity(productMixDto);

        Production production = productionRepository.findProductionById(productMixDto.getProductionId())
                .orElseThrow(() -> new EntityException("You can't make a production mix without being in a production", HttpStatus.FORBIDDEN));

        Product product = productRepository.findById(productMix.getProduct().getId())
                .orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));

        productMix.setProduction(production);



        // Let the product handle the relationship update
        product.addProductMix(productMix);

        // Optional: save product instead of productMix to persist both
        productRepository.save(product); // because cascade will save the mix too

        return productMixMapper.toDto(productMix);
    }


    @Override
    public ProductMixDto updateThisProductMix(ProductMixDto productMixDto) {
        return null;
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
    public void deleteThisProductMix(long productMixId) {
        productMixRepository.findProductMixById(productMixId).orElseThrow(() -> new EntityException("Product Mix not found", HttpStatus.NOT_FOUND));
    }

}
