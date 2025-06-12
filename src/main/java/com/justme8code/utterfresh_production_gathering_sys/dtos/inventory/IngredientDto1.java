package com.justme8code.utterfresh_production_gathering_sys.dtos.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Ingredient}
 */
@Value
public class IngredientDto1 implements Serializable {
    Long id;
    String name;
    String uom;
    List<RawMaterialDto1> rawMaterials;

    /**
     * DTO for {@link RawMaterial}
     */
    @Value
    public static class RawMaterialDto1 implements Serializable {
        Long id;
        String name;
    }
}