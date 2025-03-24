package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDtoWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;
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
    public ResponseEntity<ProductionDto> createProductionReq(@RequestBody ProductionPayload productionPayload) {
        ProductionDto createdProduction = productionService.createProduction(productionPayload);
        return new ResponseEntity<>(createdProduction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionDto> getProduction(@PathVariable long id) {
        ProductionDto productionDto = productionService.getProductionById(id);
        return new ResponseEntity<>(productionDto, HttpStatus.OK);
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

    @PostMapping("/{productionId}")
    public ResponseEntity<Production.ProductionStatus> setProductionStatus(@RequestParam Production.ProductionStatus productionStatus, @PathVariable int productionId) {
        productionService.setProductionStatus(productionId, productionStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/dynamic")
    public ResponseEntity<ProductionDtoWithDynamicData> getProductionWithDynamicData(@PathVariable long id) {
        ProductionDtoWithDynamicData productionDtoWithDynamicData = productionService.getProductionWithDynamicData(id);
        return new ResponseEntity<>(productionDtoWithDynamicData, HttpStatus.OK);
    }

    @PostMapping("/{id}/dynamic")
    public ResponseEntity<Void> addProductionDynamicData(@PathVariable long id, @RequestBody DynamicData dynamicData) {
        productionService.createProductionDynamicData(id,dynamicData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
