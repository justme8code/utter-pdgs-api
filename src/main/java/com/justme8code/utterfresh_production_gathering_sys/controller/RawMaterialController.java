package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.RawMaterialsPayload;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @PostMapping
    public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody Map<String,String> rM) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(rM.get("name"));
        rawMaterialService.storeRawMaterial(rawMaterial);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<String> createRawMaterials(@RequestBody List<Map<String,String >> rawMaterials) {
        List<RawMaterial> rMS = rawMaterials.stream().map(stringStringMap -> {
            RawMaterial rawMaterial = new RawMaterial();
            rawMaterial.setName(stringStringMap.get("name"));
            return rawMaterial;
        }).toList();

        rawMaterialService.createRawMaterials(rMS);
        return ResponseEntity.ok("Raw material created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> getRawMaterialById(@PathVariable Long id) {
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
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