package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.IngredientsPayload;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    public ResponseEntity<List<IngredientDto>> createIngredient(@RequestBody List<IngredientDto> ingredients) {
        return new ResponseEntity<>(ingredientService.createIngredients(ingredients), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable Long id) {
        IngredientDto ingredient = ingredientService.getIngredientById(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<IngredientDto> getIngredientByName(@RequestParam String name) {
        IngredientDto ingredient = ingredientService.getIngredientByName(name);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        List<IngredientDto> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientById(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ingredientId}/raw-materials")
    public ResponseEntity<Void> addRawMaterialToIngredient(@PathVariable Long ingredientId, @RequestBody RawMaterial rawMaterial) {
        ingredientService.addRawMaterialToIngredient(ingredientId, rawMaterial);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{ingredientId}/raw-materials/{rawMaterialId}")
    public ResponseEntity<Void> removeRawMaterialFromIngredient(@PathVariable Long ingredientId, @PathVariable Long rawMaterialId) {
        ingredientService.removeRawMaterialFromIngredient(ingredientId, rawMaterialId);
        return ResponseEntity.noContent().build();
    }
}