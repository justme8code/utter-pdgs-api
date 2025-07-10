package com.justme8code.utterfresh_production_gathering_sys.controller.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixProdStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.productmix.ProductionMixService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productions/{productionId}/product-mixes")
public class ProductMixController {
    private final ProductionMixService productionMixService;

    public ProductMixController(ProductionMixService productionMixService) {
        this.productionMixService = productionMixService;
    }

    // create a product mix.
    @PostMapping
    public ResponseEntity<ProductMixProdStoreDto> createProductMix(@RequestBody ProductMixDto productMixDto, @PathVariable long productionId) {
        ProductMixProdStoreDto data = productionMixService.addProductMix(productMixDto, productionId);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    // fetch a product mix
    @GetMapping("/{productMixId}")
    public ResponseEntity<ProductMixDto> fetchProductMix(@PathVariable long productMixId, @PathVariable String productionId) {
        ProductMixDto pmx = productionMixService.findThisProductMix(productMixId);
        return new ResponseEntity<>(pmx, HttpStatus.OK);
    }


    @PutMapping("/{productMixId}")
    public ResponseEntity<ProductMixDto> updateProductMix(@RequestBody ProductMixDto productMixDto, @PathVariable long productMixId, @PathVariable String productionId) {
        ProductMixDto pmx = productionMixService.updateThisProductMix(productMixDto, productMixId);
        return new ResponseEntity<>(pmx, HttpStatus.OK);
    }

    // Delete a product mix
    // fetch a product mix
    @DeleteMapping("/{productMixId}")
    public ResponseEntity<ProductMixDto> deleteProductMix(@PathVariable long productMixId, @PathVariable String productionId) {
        productionMixService.deleteThisProductMix(productMixId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductMixDto>> fetchProductMixesBySearch(@RequestParam(required = false) String search, Pageable pageable, @PathVariable String productionId) {
        Page<ProductMixDto> productMixes;
        if (search == null || search.isEmpty()) {
            productMixes = productionMixService.findAllProductMix(pageable); // Fallback to the original function if no search
        } else {
            productMixes = productionMixService.findProductMixesBySearch(search, pageable); // Call the search function
        }
        return new ResponseEntity<>(productMixes, HttpStatus.OK);
    }


}
