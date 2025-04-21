package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.MaterialToIngredient}
 */
@Value
public class MaterialToIngredientDto implements Serializable {
    Long id;
    PurchaseEntryDto1 purchaseEntry;
    double totalUsable;
    double productionLost;
    int batch;
    double litresPerKg;
    double costPerLitre;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry}
     */
    @Value
    public static class PurchaseEntryDto1 implements Serializable {
        Long id;
        RawMaterialDto1 rawMaterial;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
     */
    @Value
    public static class RawMaterialDto1 implements Serializable {
        Long id;
        String name;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
     */
    @Value
    public static class IngredientDto2 implements Serializable {
        Long id;
        String name;
    }
}
