package com.justme8code.utterfresh_production_gathering_sys.controller;


import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ReturnPurchaseAndTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.PurchaseTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/transfers")

public class InventoryTransferController {


    private final PurchaseTransferService purchaseTransferService;

    public InventoryTransferController(PurchaseTransferService purchaseTransferService) {
        this.purchaseTransferService = purchaseTransferService;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseTransferDto>> fetchAvailablePurchaseTransfers() {
        List<PurchaseTransferDto> avPT = purchaseTransferService.getAvailablePurchaseTransfers();
        return ResponseEntity.ok().body(avPT);
    }

    @PostMapping("/productions/{productionId}")
    public ResponseEntity<ReturnPurchaseAndTransferDto> createPurchaseTransfer(@RequestParam(name = "purchaseToTransfer") Long purchaseToTransfer, @PathVariable Long productionId) {
        var r = purchaseTransferService.transferPurchaseToProduction(productionId,purchaseToTransfer);
        return ResponseEntity.ok().body(r);
    }
}
