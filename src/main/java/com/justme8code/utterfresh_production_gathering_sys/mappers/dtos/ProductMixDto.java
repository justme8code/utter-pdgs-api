package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductMix}
 */
@Value
public class ProductMixDto implements Serializable {
    Long id;
    Long productionId;
    Long productId;
    List<IngredientUsageDto> ingredientUsages;
    Double totalLitersUsed;
    Integer qty;
    Double brixOnDiluent;
    Double initialBrix;
    Double finalBrix;
    Double initialPH;
    Double finalPH;
}