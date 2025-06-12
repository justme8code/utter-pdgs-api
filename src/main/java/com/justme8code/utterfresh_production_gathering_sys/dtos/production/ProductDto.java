package com.justme8code.utterfresh_production_gathering_sys.dtos.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
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