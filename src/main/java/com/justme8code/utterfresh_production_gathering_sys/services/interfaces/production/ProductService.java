package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;

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
    ProductDto updateThisProduct(long productId, ProductDto productDto);

    List<ProductMixDto> fetchAllProductMixesByProductId(long productId);
}
