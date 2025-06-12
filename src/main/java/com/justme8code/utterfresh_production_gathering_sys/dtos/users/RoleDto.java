package com.justme8code.utterfresh_production_gathering_sys.dtos.users;

import com.justme8code.utterfresh_production_gathering_sys.models.users.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.users.UserRole;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
@Value
public class RoleDto implements Serializable {
    Long id;
    UserRole userRole;
}