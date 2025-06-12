package com.justme8code.utterfresh_production_gathering_sys.dtos.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Ingredient}
 */
@Value
public class IngredientDto implements Serializable {
    Long id;
    String name;
    String uom;

}