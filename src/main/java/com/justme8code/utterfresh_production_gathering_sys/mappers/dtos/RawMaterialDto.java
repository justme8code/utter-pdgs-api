package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
 */
@Value
public class RawMaterialDto implements Serializable {
    LocalDateTime createdAt;
    Long id;
    String name;
}