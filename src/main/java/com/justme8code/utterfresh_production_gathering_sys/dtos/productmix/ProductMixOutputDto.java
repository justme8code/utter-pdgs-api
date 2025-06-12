package com.justme8code.utterfresh_production_gathering_sys.dtos.productmix;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMixIngredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link ProductMix}
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
     * DTO for {@link Product}
     */
    @Value
    public static class ProductDto1 implements Serializable {
        Long id;
        String name;
        String unitOfMeasure;
    }

    /**
     * DTO for {@link ProductMixIngredient}
     */
    @Value
    public static class ProductMixIngredientDto1 implements Serializable {
        Long id;
        IngredientDto2 ingredient;
        Double litresUsed;

        /**
         * DTO for {@link Ingredient}
         */
        @Value
        public static class IngredientDto2 implements Serializable {
            Long id;
            String name;
        }
    }
}