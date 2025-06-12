package com.justme8code.utterfresh_production_gathering_sys.dtos.inventory;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurement}
 */
@Value
public class UnitOfMeasurementDto implements Serializable {
    Long id;
    String name;
    String abbrev;
}