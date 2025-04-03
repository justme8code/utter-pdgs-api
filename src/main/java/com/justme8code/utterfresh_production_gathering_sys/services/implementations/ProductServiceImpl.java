package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto fetchThisProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityException("Product with "+productId+" not found",HttpStatus.NOT_FOUND));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> fetchAllProducts() {
        List<ProductDto> productDtos = productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto createANewProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteThisProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateThisProduct(long productId,ProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityException("Product not found", HttpStatus.NOT_FOUND));
        product = productMapper.partialUpdate(productDto, product );
        return productMapper.toDto(productRepository.save(product));
    }
}
