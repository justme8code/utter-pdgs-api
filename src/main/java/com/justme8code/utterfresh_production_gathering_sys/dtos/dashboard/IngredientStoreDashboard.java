package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore}
 */
@Value
public class IngredientStoreDashboard implements Serializable {
    Long id;
    IngredientDto2 ingredient;
    double usableLitresLeft;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient}
     */
    @Value
    public static class IngredientDto2 implements Serializable {
        String name;
        String uom;
    }
}