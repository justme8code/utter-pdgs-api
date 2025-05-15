package com.justme8code.utterfresh_production_gathering_sys.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductMixIngredient}
 */
@Value
public class ProductMixIngredientDto implements Serializable {
    Long ingredientId;
    Double litresUsed;
    IngredientDto ingredient;
}