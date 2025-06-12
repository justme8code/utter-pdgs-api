package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix}
 */
@Value
public class ProductMixDashboardData implements Serializable {
    Long id;
    ProductionDto production;
    String productName;
    Double productCount;
    Double totalLitersUsed;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.Production}
     */
    @Value
    public static class ProductionDto implements Serializable {
        Long id;
        String name;
    }
}