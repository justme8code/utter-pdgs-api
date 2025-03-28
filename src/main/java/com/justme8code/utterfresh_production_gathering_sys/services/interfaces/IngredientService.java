package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import java.util.List;

public interface IngredientService {
    IngredientDto createIngredient(IngredientDto ingredient);
    IngredientDto getIngredientById(Long id);
    IngredientDto getIngredientByName(String name);
    List<IngredientDto> createIngredients(List<IngredientDto> ingredientDtoList);
    List<IngredientDto> getAllIngredients();
    void deleteIngredientById(Long id);
    void addRawMaterialToIngredient(Long ingredientId, RawMaterial rawMaterial);
    void removeRawMaterialFromIngredient(Long ingredientId, Long rawMaterialId);
}