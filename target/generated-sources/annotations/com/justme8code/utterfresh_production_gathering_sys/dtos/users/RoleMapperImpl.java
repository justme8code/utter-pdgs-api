package com.justme8code.utterfresh_production_gathering_sys.dtos.users;

import com.justme8code.utterfresh_production_gathering_sys.models.users.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.users.UserRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T19:52:50+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setUserRole( roleDto.getUserRole() );

        return role;
    }

    @Override
    public RoleDto toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        Long id = null;
        UserRole userRole = null;

        id = role.getId();
        userRole = role.getUserRole();

        RoleDto roleDto = new RoleDto( id, userRole );

        return roleDto;
    }

    @Override
    public Role partialUpdate(RoleDto roleDto, Role role) {
        if ( roleDto == null ) {
            return role;
        }

        if ( roleDto.getId() != null ) {
            role.setId( roleDto.getId() );
        }
        if ( roleDto.getUserRole() != null ) {
            role.setUserRole( roleDto.getUserRole() );
        }

        return role;
    }
}
