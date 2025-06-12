package com.justme8code.utterfresh_production_gathering_sys.controller.inventory;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto1;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.inventory.IngredientService;
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
    public ResponseEntity<List<IngredientDto1>> createIngredient(@RequestBody List<IngredientDto1> ingredients) {
        return new ResponseEntity<>(ingredientService.createIngredients(ingredients), HttpStatus.OK);
    }

    @GetMapping("/search/by-raw-material-names")
    public ResponseEntity<List<IngredientDto1>> getIngredientsByNames(@RequestParam(required = false) List<String> rawMaterialNames) {
        List<IngredientDto1> ingredients = ingredientService.getIngredientsByNames(rawMaterialNames);
        return ingredients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(ingredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto1> getIngredientById(@PathVariable Long id) {
        IngredientDto1 ingredient = ingredientService.getIngredientById(id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<IngredientDto1> getIngredientByName(@RequestParam String name) {
        IngredientDto1 ingredient = ingredientService.getIngredientByName(name);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }


    @GetMapping
    public ResponseEntity<List<IngredientDto1>> getAllIngredients() {
        List<IngredientDto1> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/{ingredientId}")
    public ResponseEntity<IngredientDto1> updateIngredient(@RequestBody IngredientDto1 ingredient, @PathVariable long ingredientId) {
        IngredientDto1 ingredientDto1 = ingredientService.updateIngredient(ingredientId, ingredient);
        return ResponseEntity.ok(ingredientDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientById(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.noContent().build();
    }

    /* @PostMapping("/{ingredientId}/raw-materials")
     public ResponseEntity<Void> updateIngredient(@PathVariable Long ingredientId, @RequestBody RawMaterial rawMaterial) {
         ingredientService.addRawMaterialToIngredient(ingredientId, rawMaterial);
         return ResponseEntity.noContent().build();
     }
 */
    @DeleteMapping("/{ingredientId}/raw-materials/{rawMaterialId}")
    public ResponseEntity<Void> removeRawMaterialFromIngredient(@PathVariable Long ingredientId, @PathVariable Long rawMaterialId) {
        ingredientService.removeRawMaterialFromIngredient(ingredientId, rawMaterialId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rawMaterials")
    public ResponseEntity<List<IngredientDto>> getIngredientsByRawMaterialId(@RequestParam Long rawMaterialId) {
        List<IngredientDto> ingredients = ingredientService.getIngredientsByRawMaterialId(rawMaterialId);
        return ingredients.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(ingredients);
    }
}