package com.justme8code.utterfresh_production_gathering_sys.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient;
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
    List<LitersUsedForIngredientDto> litersUsedForIngredients;
    Double totalLitersUsed;
    Integer qty;
    Double brixOnDiluent;
    Double initialBrix;
    Double finalBrix;
    Double initialPH;
    Double finalPH;

    /**
     * DTO for {@link ProductMixIngredient}
     */
    @Value
    public static class LitersUsedForIngredientDto implements Serializable {
        Long id;
        Double litresUsed;
    }
}