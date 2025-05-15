package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @PostMapping()
    public ResponseEntity<List<RawMaterialDto>> createRawMaterial(@RequestBody List<RawMaterialDto> rawMaterials) {
        List<RawMaterialDto> rawMaterialDtos = rawMaterialService.createRawMaterials(rawMaterials);
        return new ResponseEntity<>(rawMaterialDtos, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialDto> getRawMaterialById(@PathVariable Long id) {
        RawMaterialDto rawMaterial = rawMaterialService.getRawMaterialById(id);
        return rawMaterial != null ? ResponseEntity.ok(rawMaterial) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialDto>> getAllRawMaterials() {
        List<RawMaterialDto> rawMaterials = rawMaterialService.getAllRawMaterials();
        return ResponseEntity.ok(rawMaterials);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterialById(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterialById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rawMaterialId}/ingredients")
    public ResponseEntity<Void> addIngredientToRawMaterial(@PathVariable Long rawMaterialId, @RequestBody List<IngredientDto> ingredients) {
        rawMaterialService.addIngredientToRawMaterial(rawMaterialId, ingredients);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{rawMaterialId}/ingredients/{ingredientId}")
    public ResponseEntity<Void> removeIngredientFromRawMaterial(@PathVariable Long rawMaterialId, @PathVariable Long ingredientId) {
        rawMaterialService.removeIngredientFromRawMaterial(rawMaterialId, ingredientId);
        return ResponseEntity.noContent().build();
    }
}