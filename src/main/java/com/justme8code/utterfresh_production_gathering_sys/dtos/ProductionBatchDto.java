package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch}
 */
@Value
public class ProductionBatchDto implements Serializable {
    Long id;
    boolean active;
    String name;
}