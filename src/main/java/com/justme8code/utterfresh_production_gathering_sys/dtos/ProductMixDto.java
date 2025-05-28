package com.justme8code.utterfresh_production_gathering_sys.dtos;

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
    List<ProductMixIngredientDto> productMixIngredients;
    Double productCount;
    Double totalLitersUsed;
    Double brixOnDiluent;
    Double initialBrix;
    Double finalBrix;
    Double initialPH;
    Double finalPH;
}