package com.justme8code.utterfresh_production_gathering_sys.dtos.users;

import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.users.User;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String fullName;
    StaffDto1 staff;
    String email;
    String phone;
    Set<RoleDto> roles;

    /**
     * DTO for {@link Staff}
     */
    @Value
    public static class StaffDto1 implements Serializable {
        Long id;
        String profession;
        String companyRole;
    }
}