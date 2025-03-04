package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.IngredientService;
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

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.storeIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
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