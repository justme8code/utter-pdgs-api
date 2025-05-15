package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.IngredientDto1;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;

import java.util.List;

public interface IngredientService {
    IngredientDto1 createIngredient(IngredientDto1 ingredient);

    IngredientDto1 getIngredientById(Long id);

    IngredientDto1 getIngredientByName(String name);

    List<IngredientDto1> getIngredientsByNames(List<String> names);

    List<IngredientDto1> createIngredients(List<IngredientDto1> ingredientDtoList);

    List<IngredientDto1> getAllIngredients();

    IngredientDto1 updateIngredient(long ingredientId, IngredientDto1 ingredient);

    void deleteIngredientById(Long id);

    void addRawMaterialToIngredient(Long ingredientId, RawMaterial rawMaterial);

    void removeRawMaterialFromIngredient(Long ingredientId, Long rawMaterialId);
    List<IngredientDto> getIngredientsByRawMaterialId(Long rawMaterialId);
}