package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
 */
@Value
public class IngredientDto implements Serializable {
    LocalDateTime createdAt;
    Long id;
    String name;
}