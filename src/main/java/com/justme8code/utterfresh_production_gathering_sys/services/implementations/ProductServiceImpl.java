package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.Product;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final IngredientRepository ingredientRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,IngredientRepository ingredientRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.ingredientRepository = ingredientRepository;
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

        List<Long> ingredientIds = product.getIngredients().stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());

        List<Ingredient> existingIngredients;
        if(product.getIngredients().isEmpty()) {
            existingIngredients = new ArrayList<>();
        }else{
            // Fetch all existing ingredients in a single query
            existingIngredients = ingredientRepository.findAllById(ingredientIds);
            // Validate that all ingredients exist
            if (existingIngredients.size() != ingredientIds.size()) {
                throw new EntityException("Some ingredients do not exist in the database", HttpStatus.NOT_FOUND);
            }
        }
        product.setIngredients(existingIngredients);
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
