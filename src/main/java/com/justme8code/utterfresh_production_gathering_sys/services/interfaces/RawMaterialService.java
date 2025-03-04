package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import java.util.List;

public interface RawMaterialService {
    void storeRawMaterial(RawMaterial rawMaterial);
    RawMaterial getRawMaterialById(Long id);
    List<RawMaterial> getAllRawMaterials();
    void deleteRawMaterialById(Long id);
    void addIngredientToRawMaterial(Long rawMaterialId, Ingredient ingredient);
    void removeIngredientFromRawMaterial(Long rawMaterialId, Long ingredientId);
}