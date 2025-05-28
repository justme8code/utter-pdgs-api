package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchasePRStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions/{productionId}/purchase-entries")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchasePRStoreDto> createPurchaseEntry(
            @PathVariable Long productionId,
            @RequestBody PurchaseDto purchaseDto) {

        PurchasePRStoreDto purchasePRStoreDto = purchaseService.createPurchaseEntry(productionId, purchaseDto);
        return new ResponseEntity<>(purchasePRStoreDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getPurchaseEntriesByProductionId(@PathVariable Long productionId) {
        return ResponseEntity.ok(purchaseService.getNonDeletedPurchaseEntriesByProductionId(productionId));
    }

    @PutMapping("/{purchaseId}")
    public ResponseEntity<PurchaseDto> updatePurchaseEntry(
            @RequestBody PurchaseDto purchaseDto, @PathVariable Long productionId, @PathVariable Long purchaseId) {
        return ResponseEntity.ok(purchaseService.updatePurchaseEntry(productionId,purchaseId, purchaseDto));
    }

    @DeleteMapping("/{purchaseEntryId}")
    public ResponseEntity<Void> deletePurchaseEntry(@PathVariable Long purchaseEntryId, @PathVariable Long productionId) {
        purchaseService.deletePurchaseEntry(productionId, purchaseEntryId);
        return ResponseEntity.noContent().build();
    }
}
