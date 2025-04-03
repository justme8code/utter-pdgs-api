package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
    String unitOfMeasure;
    String category;
    VariantDto variant;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Variant}
     */
    @Value
    public static class VariantDto implements Serializable {
        Long id;
        Long productId;
        String name;
        String description;
    }
}