package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient}
 */
@Value
public class ProductMixIngredientDto implements Serializable {
    Long id;
    Long ingredientId;
    Double litresUsed;
}