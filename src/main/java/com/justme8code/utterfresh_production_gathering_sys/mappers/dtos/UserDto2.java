package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
 */
@Value
public class UserDto2 implements Serializable {
    Long id;
    String fullName;
    String email;
    String phone;
    Set<RoleDto> roles;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Role}
     */
    @Value
    public static class RoleDto implements Serializable {
        Long id;
        UserRole userRole;
    }
}