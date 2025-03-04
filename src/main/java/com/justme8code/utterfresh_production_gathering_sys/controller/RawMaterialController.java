package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
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

    @PostMapping
    public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        rawMaterialService.storeRawMaterial(rawMaterial);
        return ResponseEntity.ok(rawMaterial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> getRawMaterialById(@PathVariable Long id) {
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        return rawMaterial != null ? ResponseEntity.ok(rawMaterial) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<RawMaterial>> getAllRawMaterials() {
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        return ResponseEntity.ok(rawMaterials);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRawMaterialById(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterialById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rawMaterialId}/ingredients")
    public ResponseEntity<Void> addIngredientToRawMaterial(@PathVariable Long rawMaterialId, @RequestBody Ingredient ingredient) {
        rawMaterialService.addIngredientToRawMaterial(rawMaterialId, ingredient);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{rawMaterialId}/ingredients/{ingredientId}")
    public ResponseEntity<Void> removeIngredientFromRawMaterial(@PathVariable Long rawMaterialId, @PathVariable Long ingredientId) {
        rawMaterialService.removeIngredientFromRawMaterial(rawMaterialId, ingredientId);
        return ResponseEntity.noContent().build();
    }
}