package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
    String unitOfMeasure;
    Long totalProductMixCount;
    List<IngredientDto> ingredients;
}