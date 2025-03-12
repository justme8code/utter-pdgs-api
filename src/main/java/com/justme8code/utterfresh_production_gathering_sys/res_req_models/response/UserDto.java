package com.justme8code.utterfresh_production_gathering_sys.res_req_models.response;

import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String fullName;
    Long staffId;
    String staffProfession;
    String staffCompanyRole;
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