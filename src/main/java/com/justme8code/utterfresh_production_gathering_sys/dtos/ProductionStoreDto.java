package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductionStore}
 */
@Value
public class ProductionStoreDto implements Serializable {
    Long id;
    Long productionId;
    List<IngredientStoreDto> ingredientStores;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.IngredientStore}
     */
    @Value
    public static class IngredientStoreDto implements Serializable {
        Long id;
        IngredientDto ingredient;
        double usableLitresLeft;
    }
}