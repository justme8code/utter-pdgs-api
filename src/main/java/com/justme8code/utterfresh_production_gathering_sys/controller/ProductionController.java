package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
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

    @PostMapping("/staffs/{staffId}")
    public ResponseEntity<ProductionDto> createProductionReq(@RequestBody Production production, @PathVariable Long staffId) {
        ProductionDto createdProduction = productionService.createProduction(production,staffId);
        return new ResponseEntity<>(createdProduction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductionDto>> getProductions(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(productionService.getProductions(page,size), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductionInfo>> searchProductions(@RequestParam String name) {
        System.out.println(name);
        List<ProductionInfo> productions = productionService.getProductionsByName(name);
        return ResponseEntity.ok(productions);
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<ProductionInfo>> searchProductionsByDate(@RequestParam String startDate) {
        List<ProductionInfo> productions = productionService.getProductionsByStartDate(startDate);
        return ResponseEntity.ok(productions);
    }
}
