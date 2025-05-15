package com.justme8code.utterfresh_production_gathering_sys.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Role}
 */
@Value
public class RoleDto implements Serializable {
    Long id;
    UserRole userRole;
}