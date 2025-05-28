package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.dtos.*;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions")
public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }


    @PostMapping()
    public ResponseEntity<ProductionDto> createProduction(@RequestBody ProductionDto productionDto) {
        ProductionDto createdProduction = productionService.createProduction(productionDto);
        return new ResponseEntity<>(createdProduction, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductionDto> getProduction(@PathVariable long id) {
        ProductionDto productionDto = productionService.getProductionById(id);
        return new ResponseEntity<>(productionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductionDto>> getProductions(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(productionService.getProductions(page, size), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductionDto>> searchProductions(@RequestParam String name) {
        System.out.println(name);
        List<ProductionDto> productions = productionService.getProductionsByName(name);
        return ResponseEntity.ok(productions);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<ProductionDto>> searchProductionsByDate(@RequestParam String startDate) {
        List<ProductionDto> productions = productionService.getProductionsByStartDate(startDate);
        return ResponseEntity.ok(productions);
    }


    // production to product mix controller
    @GetMapping("/{productionId}/product-mixes")
    public ResponseEntity<List<ProductMixDto>> fetchProductionMixes(@PathVariable long productionId) {
        List<ProductMixDto> productMixDtos = productionService.getProductMix(productionId);
        return ResponseEntity.ok(productMixDtos);
    }

    @GetMapping("/{productionId}/product-mix-outputs")
    public ResponseEntity<List<ProductMixOutputDto>> fetchProductMixOutputs(@PathVariable long productionId) {
        List<ProductMixOutputDto> productMixOutputs = productionService.getProductMixOutput(productionId);
        System.out.println(productMixOutputs);
        return ResponseEntity.ok(productMixOutputs);
    }

    @GetMapping("/{productionId}/stores")
    public ResponseEntity<ProductionStoreDto> getProductionStoreByProductionId(@PathVariable long productionId) {
        return ResponseEntity.ok(productionService.getProductionStoreByProductionId(productionId));
    }

    @DeleteMapping("/{productionId}")
    public ResponseEntity<Void> deleteProduction(@PathVariable long productionId) {
        productionService.deleteProduction(productionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productionId}/complete")
    public ResponseEntity<ProductionFullDataDto> getProductionFullData(@PathVariable Long productionId) {
        ProductionFullDataDto pfdDto = productionService.getProductionFullDetails(productionId);
        return ResponseEntity.ok(pfdDto);
    }

    @PostMapping("/{productionId}/finalize")
    public ResponseEntity<Void> finalizeProduction(@PathVariable Long productionId) {
        productionService.finalizeProduction(productionId);
        return ResponseEntity.noContent().build();
    }
}

