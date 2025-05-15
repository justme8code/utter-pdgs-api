package com.justme8code.utterfresh_production_gathering_sys.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
 */
public record StaffDto(Long id, String userFullName) implements Serializable {
}