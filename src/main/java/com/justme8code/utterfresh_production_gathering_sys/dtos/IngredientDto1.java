package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
 */
@Value
public class IngredientDto1 implements Serializable {
    Long id;
    String name;
    List<RawMaterialDto1> rawMaterials;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
     */
    @Value
    public static class RawMaterialDto1 implements Serializable {
        Long id;
        String name;
    }
}