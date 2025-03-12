package com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
 */
@Value
public class CreateUserRequest implements Serializable {
    Long id;
    String fullName;
    StaffDto staff;
    String pwd;
    String email;
    String phone;
    Set<RoleDto> roles;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
     */
    @Value
    public static class StaffDto implements Serializable {
        String profession;
        String companyRole;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Role}
     */
    @Value
    public static class RoleDto implements Serializable {
        Long id;
    }
}