package com.justme8code.utterfresh_production_gathering_sys.controller.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.CPDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionPayload;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.productions.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions/{productionId}/purchases/{purchaseId}/conversions")
public class ConversionController {

    private final ConversionService conversionService;

    @Autowired
    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<CPDto> createMTI(@RequestBody ConversionPayload conversionPayload, @PathVariable int purchaseId, @PathVariable long productionId) {
        CPDto mti = conversionService.createMaterialToIngredient(conversionPayload, purchaseId, productionId);
        return new ResponseEntity<>(mti, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConversionDto>> getMTI(@PathVariable long purchaseId, @PathVariable long productionId) {
        List<ConversionDto> listOfMTI = conversionService
                .getMaterialToIngredientsByPurchaseEntryId(purchaseId, productionId);
        return new ResponseEntity<>(listOfMTI, HttpStatus.OK);
    }
}
