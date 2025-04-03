package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.SupplierDto;
import com.justme8code.utterfresh_production_gathering_sys.models.SupplierDtoPayload;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    //Supplier service
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    //Create suppliers
    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody  SupplierDtoPayload payload) {
        SupplierDto savedSupplier = supplierService.createSupplier(payload);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    // Create a list of suppliers
    @PostMapping("/lists")
    public ResponseEntity<List<SupplierDto>> createSuppliers(@RequestBody  List<SupplierDtoPayload> payloads) {
        List<SupplierDto> suppliers = supplierService.createSuppliers(payloads);
        return new ResponseEntity<>(suppliers, HttpStatus.CREATED);
    }

    // Get supplier
    @GetMapping("/lists")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierDto> suppliers = supplierService.getSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    //update Supplier
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@RequestBody SupplierDtoPayload payload, @PathVariable long id) {
        SupplierDto updatedSupplier = supplierService.updateSupplier(id,payload);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    // Delete  Supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDto> deleteSupplier(@PathVariable long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
