package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Product;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // fetch this product
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> fetch(@PathVariable long productId) {
        ProductDto productDto = productService.fetchThisProduct(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    // fetch all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchAll() {
        List<ProductDto> products = productService.fetchAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // create a product
    @PostMapping
    public ResponseEntity<ProductDto> create(ProductDto productDto) {
        ProductDto p = productService.createANewProduct(productDto);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    // update this product
    @PutMapping
    public ResponseEntity<ProductDto> update(Long id, ProductDto productDto) {
        ProductDto p = productService.updateThisProduct(id, productDto);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    // delete this product
    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        productService.deleteThisProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
