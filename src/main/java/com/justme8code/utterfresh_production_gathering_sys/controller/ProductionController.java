package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.ProductionDtoWithDataResponse;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import com.justme8code.utterfresh_production_gathering_sys.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<ProductionDtoWithDataResponse> getProductionWithDynamicData(@PathVariable long id) {
       try{
           ProductionWithDynamicData productionDtoWithDynamicData = productionService.getProductionWithDynamicData(id);
           ProductionDtoWithDataResponse dataResponse = withDataResponse(productionDtoWithDynamicData);
           return new ResponseEntity<>(dataResponse, HttpStatus.OK);
       } catch (Exception e) {
           System.out.println(e);
           throw new EntityException("Could not get production",HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping("/{id}/dynamic")
    public ResponseEntity<Void> addProductionDynamicData(@PathVariable long id, @RequestBody Map<String,Object> dynamicData) {
        productionService.createProductionDynamicData(id,dynamicData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/dynamic")
    public ResponseEntity<Void> updateProductionWithDynamicData(@PathVariable long id, @RequestBody Map<String,Object> dynamicData) {
        productionService.updateProductionDynamicData(id,dynamicData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // production to product mix controller
    @GetMapping("/{productionId}/product-mixes")
    public ResponseEntity<List<ProductMixDto>> fetchProductionMixes(@PathVariable long productionId) {
        List<ProductMixDto> productMixDtos = productionService.getProductMix(productionId);
        return ResponseEntity.ok(productMixDtos);
    }


    private ProductionDtoWithDataResponse withDataResponse(ProductionWithDynamicData productionWithDynamicData) throws Exception {
        ProductionDtoWithDataResponse withDataResponse = new ProductionDtoWithDataResponse();
        ProductionWithDynamicData.DynamicDataDto dD = productionWithDynamicData.getDynamicData();
        withDataResponse.setId(productionWithDynamicData.getId());
        withDataResponse.setName(productionWithDynamicData.getName());
        withDataResponse.setStartDate(productionWithDynamicData.getStartDate());
        withDataResponse.setStaff(productionWithDynamicData.getStaff());
        withDataResponse.setEndDate(productionWithDynamicData.getEndDate());
        withDataResponse.setProductionNumber(productionWithDynamicData.getProductionNumber());
        withDataResponse.setStatus(productionWithDynamicData.getStatus());
        withDataResponse.setStatus(productionWithDynamicData.getStatus());
        withDataResponse.setProductionBatches(productionWithDynamicData.getProductionBatches());
        withDataResponse.setDynamicDataId(productionWithDynamicData.getDynamicData().getId());
        withDataResponse.setDynamicDataName(productionWithDynamicData.getDynamicData().getName());
        String jsonData = dD.getJsonData();
        withDataResponse.setDynamicData(jsonData==null?new HashMap<>():JsonUtils.fromJson(jsonData));
        System.out.println(withDataResponse);
        return withDataResponse;
    }
}
