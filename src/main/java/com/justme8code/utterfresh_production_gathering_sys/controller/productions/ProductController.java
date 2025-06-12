package com.justme8code.utterfresh_production_gathering_sys.controller.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.productions.PurchaseService;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

    public ProductController(ProductService productService, PurchaseService purchaseService) {
        this.productService = productService;
        this.purchaseService = purchaseService;
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
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        ProductDto p = productService.createANewProduct(productDto);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    // update this product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        ProductDto p = productService.updateThisProduct(productId, productDto);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    // delete this product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        productService.deleteThisProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get product mixes
    @GetMapping("/{productId}/product-mixes")
    public ResponseEntity<List<ProductMixDto>> productMixesByProductId(@PathVariable String productId) {
        List<ProductMixDto> productMixDtos = productService.fetchAllProductMixesByProductId(Long.parseLong(productId));
        return new ResponseEntity<>(productMixDtos, HttpStatus.OK);
    }


}
