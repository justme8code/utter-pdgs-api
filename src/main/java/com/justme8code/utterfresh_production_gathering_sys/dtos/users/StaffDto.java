package com.justme8code.utterfresh_production_gathering_sys.dtos.users;

import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;

import java.io.Serializable;

/**
 * DTO for {@link Staff}
 */
public record StaffDto(Long id, String userFullName) implements Serializable {
}