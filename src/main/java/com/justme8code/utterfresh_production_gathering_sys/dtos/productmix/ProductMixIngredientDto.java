package com.justme8code.utterfresh_production_gathering_sys.dtos.productmix;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMixIngredient;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductMixIngredient}
 */
@Value
public class ProductMixIngredientDto implements Serializable {
    Long id;
    Long ingredientId;
    Double litresUsed;
}