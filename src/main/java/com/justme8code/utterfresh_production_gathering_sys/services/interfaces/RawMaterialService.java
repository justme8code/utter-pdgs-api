package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import java.util.List;

public interface RawMaterialService {
    RawMaterialDto createRawMaterial(RawMaterialDto rawMaterial);
    RawMaterialDto getRawMaterialById(Long id);
    List<RawMaterialDto> createRawMaterials(List<RawMaterialDto> rawMaterials);
    List<RawMaterialDto> getAllRawMaterials();
    void deleteRawMaterialById(Long id);
    void addIngredientToRawMaterial(Long rawMaterialId, List<IngredientDto> ingredients);
    void removeIngredientFromRawMaterial(Long rawMaterialId, Long ingredientId);
}