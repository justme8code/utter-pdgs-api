package com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests;

import com.justme8code.utterfresh_production_gathering_sys.dtos.RoleDto;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
 */
@Value
public class CreateUserRequestDto implements Serializable {
    Long id;
    String fullName;
    StaffDto staff;
    String pwd;
    String email;
    String phone;
    Long createdBy;
    Set<RoleDto> roles;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
     */
    @Value
    public static class StaffDto implements Serializable {
        String profession;
        String companyRole;
    }
}