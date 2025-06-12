package com.justme8code.utterfresh_production_gathering_sys.controller.inventory;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.UnitOfMeasurementDto;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurement;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.inventory.UnitOfMeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/uoms")
public class UomController {

    private final UnitOfMeasurementService unitOfMeasurementService;

    public UomController(UnitOfMeasurementService unitOfMeasurementService) {
        this.unitOfMeasurementService = unitOfMeasurementService;
    }

    // get unit of measurement

    @GetMapping("/{id}")
    public ResponseEntity<UnitOfMeasurementDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(unitOfMeasurementService.getUnitOfMeasurementById(id),HttpStatus.OK);
    }
    // get unit of measurements
    @GetMapping
    public ResponseEntity<List<UnitOfMeasurementDto>> getUnitOfMeasurements() {
        return new ResponseEntity<>(unitOfMeasurementService.getAllUnitOfMeasurements(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnitOfMeasurementDto> createUnitOfMeasurement(@RequestBody UnitOfMeasurement unitOfMeasurement) {
        return new ResponseEntity<>(unitOfMeasurementService.createUnitOfMeasurement(unitOfMeasurement), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UnitOfMeasurementDto> updateUnitOfMeasurement(@RequestBody UnitOfMeasurement unitOfMeasurement) {
        return new ResponseEntity<>(unitOfMeasurementService.updateUnitOfMeasurement(unitOfMeasurement), HttpStatus.OK);
    }

    // delete uom
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnitOfMeasurements(@PathVariable long id) {
        unitOfMeasurementService.deleteUnitOfMeasurementById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
