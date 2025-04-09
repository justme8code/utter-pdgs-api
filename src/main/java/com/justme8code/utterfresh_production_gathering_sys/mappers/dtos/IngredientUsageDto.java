package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.IngredientUsage}
 */
@Value
public class IngredientUsageDto implements Serializable {
    Long ingredientId;
    Double litresUsed;
}