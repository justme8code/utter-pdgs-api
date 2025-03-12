package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import java.util.List;

public interface IngredientService {
    void createIngredient(Ingredient ingredient);
    Ingredient getIngredientById(Long id);
    IngredientDto getIngredientByName(String name);
    void createIngredients(List<Ingredient> ingredients);
    List<Ingredient> getAllIngredients();
    void deleteIngredientById(Long id);
    void addRawMaterialToIngredient(Long ingredientId, RawMaterial rawMaterial);
    void removeRawMaterialFromIngredient(Long ingredientId, Long rawMaterialId);
}