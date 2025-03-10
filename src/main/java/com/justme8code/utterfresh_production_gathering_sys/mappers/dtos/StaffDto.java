package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
 */
public record StaffDto(Long id, Long userId, String userFullName, String profession,
                       String companyRole) implements Serializable {
}