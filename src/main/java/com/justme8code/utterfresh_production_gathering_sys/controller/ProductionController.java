package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productions")
public class ProductionController {
    private final ProductionService productionService;
    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/staffs/{staffId}")
    public ResponseEntity<Production> createProductionReq(@RequestBody Production production, @PathVariable Long staffId) {
        Production createdProduction = productionService.createProduction(production,staffId);
        return new ResponseEntity<>(createdProduction, HttpStatus.CREATED);
    }
}
