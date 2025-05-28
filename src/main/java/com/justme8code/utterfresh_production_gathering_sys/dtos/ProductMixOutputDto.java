package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductMix}
 */
@Value
public class ProductMixOutputDto implements Serializable {
    LocalDateTime createdAt;
    Long id;
    ProductDto1 product;
    List<ProductMixIngredientDto1> productMixIngredients;
    Double productCount;
    Double totalLitersUsed;
    Integer qty;
    Double brixOnDiluent;
    Double initialBrix;
    Double finalBrix;
    Double initialPH;
    Double finalPH;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Product}
     */
    @Value
    public static class ProductDto1 implements Serializable {
        Long id;
        String name;
        String unitOfMeasure;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient}
     */
    @Value
    public static class ProductMixIngredientDto1 implements Serializable {
        Long id;
        IngredientDto2 ingredient;
        Double litresUsed;

        /**
         * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
         */
        @Value
        public static class IngredientDto2 implements Serializable {
            Long id;
            String name;
        }
    }
}