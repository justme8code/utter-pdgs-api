package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.Product}
 */
@Value
public class ProductDashboardData implements Serializable {
    Long id;
    String name;
    Long totalProductMixCount;
}