package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductMixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-mixes")
public class ProductMixController {
    private final ProductMixService productMixService;

    public ProductMixController(ProductMixService productMixService) {
        this.productMixService = productMixService;
    }

    // create a product mix.
    @PostMapping
    public ResponseEntity<ProductMixDto> createProductMix(@RequestBody ProductMixDto productMixDto) {
        ProductMixDto pmx = productMixService.createANewProductMix(productMixDto);
        return new ResponseEntity<>(pmx, HttpStatus.CREATED);
    }

    // fetch a product mix
    @GetMapping("/{productMixId}")
    public ResponseEntity<ProductMixDto> fetchProductMix(@PathVariable long productMixId){
        ProductMixDto pmx = productMixService.findThisProductMix(productMixId);
        return new ResponseEntity<>(pmx, HttpStatus.OK);
    }

    // Delete a product mix
    // fetch a product mix
    @DeleteMapping("/{productMixId}")
    public ResponseEntity<ProductMixDto> deleteProductMix(@PathVariable long productMixId){
        productMixService.deleteThisProductMix(productMixId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
