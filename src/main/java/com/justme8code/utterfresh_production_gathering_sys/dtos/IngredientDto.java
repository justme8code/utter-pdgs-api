package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
 */
@Value
public class IngredientDto implements Serializable {
    Long id;
    String name;
}