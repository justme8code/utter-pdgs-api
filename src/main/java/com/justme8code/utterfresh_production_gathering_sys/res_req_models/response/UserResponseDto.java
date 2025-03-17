package com.justme8code.utterfresh_production_gathering_sys.res_req_models.response;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.RoleDto;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
 */
@Value
public class UserResponseDto implements Serializable {
    Long id;
    String fullName;
    StaffDto staff;
    String email;
    Set<RoleDto> roles;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
     */
    @Value
    public static class StaffDto implements Serializable {
        Long id;
        String profession;
        String companyRole;
    }
}