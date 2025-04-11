package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Product;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductRepository;
import com.justme8code.utterfresh_production_gathering_sys.models.Variant;
import com.justme8code.utterfresh_production_gathering_sys.repository.VariantRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final VariantRepository variantRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper, VariantRepository variantRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.variantRepository = variantRepository;
    }

    @Override
    public ProductDto fetchThisProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityException("Product with "+productId+" not found",HttpStatus.NOT_FOUND));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> fetchAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public ProductDto createANewProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        Variant variant = product.getVariant();
        Variant rv = variantRepository.save(variant);
        product.setVariant(rv);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void deleteThisProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public ProductDto updateThisProduct(long productId,ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));
        product = productMapper.partialUpdate(productDto, product );
        return productMapper.toDto(productRepository.save(product));
    }
}
