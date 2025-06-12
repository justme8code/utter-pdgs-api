package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage}
 */
@Value
public class RUDashboardData implements Serializable {
    Long id;
    double usableWeightLeft;
    String rawMaterialName;
}