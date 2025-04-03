package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //fetch product
    ProductDto fetchThisProduct(long productId);
    // fetch all list of products
    List<ProductDto> fetchAllProducts();
    // creates a product
    ProductDto createANewProduct(ProductDto productDto);
    // delete this product
    void deleteThisProduct(Long id);
    //update this product
    ProductDto updateThisProduct(long productId,ProductDto productDto);
}
