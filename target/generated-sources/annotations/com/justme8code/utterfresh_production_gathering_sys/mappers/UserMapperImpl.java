package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.RoleDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.users.RoleMapper;
import com.justme8code.utterfresh_production_gathering_sys.dtos.users.UserDto;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.users.User;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequestDto;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserResponseDto;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T10:25:26+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFullName( userDto.getFullName() );
        user.setStaff( staffDto1ToStaff( userDto.getStaff() ) );
        user.setEmail( userDto.getEmail() );
        user.setPhone( userDto.getPhone() );
        user.setRoles( roleDtoSetToRoleSet( userDto.getRoles() ) );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Set<RoleDto> roles = null;
        Long id = null;
        String fullName = null;
        UserDto.StaffDto1 staff = null;
        String email = null;
        String phone = null;

        roles = roleSetToRoleDtoSet( user.getRoles() );
        id = user.getId();
        fullName = user.getFullName();
        staff = staffToStaffDto1( user.getStaff() );
        email = user.getEmail();
        phone = user.getPhone();

        UserDto userDto = new UserDto( id, fullName, staff, email, phone, roles );

        return userDto;
    }

    @Override
    public User partialUpdate(UserDto userDto, User user) {
        if ( userDto == null ) {
            return user;
        }

        if ( userDto.getId() != null ) {
            user.setId( userDto.getId() );
        }
        if ( userDto.getFullName() != null ) {
            user.setFullName( userDto.getFullName() );
        }
        if ( userDto.getStaff() != null ) {
            if ( user.getStaff() == null ) {
                user.setStaff( new Staff() );
            }
            staffDto1ToStaff1( userDto.getStaff(), user.getStaff() );
        }
        if ( userDto.getEmail() != null ) {
            user.setEmail( userDto.getEmail() );
        }
        if ( userDto.getPhone() != null ) {
            user.setPhone( userDto.getPhone() );
        }
        if ( user.getRoles() != null ) {
            Set<Role> set = roleDtoSetToRoleSet( userDto.getRoles() );
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDtoSetToRoleSet( userDto.getRoles() );
            if ( set != null ) {
                user.setRoles( set );
            }
        }

        return user;
    }

    @Override
    public User toEntity(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userResponseDto.getId() );
        user.setFullName( userResponseDto.getFullName() );
        user.setStaff( staffDtoToStaff( userResponseDto.getStaff() ) );
        user.setEmail( userResponseDto.getEmail() );
        user.setRoles( roleDtoSetToRoleSet( userResponseDto.getRoles() ) );

        return user;
    }

    @Override
    public UserResponseDto toDto1(User user) {
        if ( user == null ) {
            return null;
        }

        Set<RoleDto> roles = null;
        Long id = null;
        String fullName = null;
        UserResponseDto.StaffDto staff = null;
        String email = null;

        roles = roleSetToRoleDtoSet( user.getRoles() );
        id = user.getId();
        fullName = user.getFullName();
        staff = staffToStaffDto( user.getStaff() );
        email = user.getEmail();

        UserResponseDto userResponseDto = new UserResponseDto( id, fullName, staff, email, roles );

        return userResponseDto;
    }

    @Override
    public User partialUpdate(UserResponseDto userResponseDto, User user) {
        if ( userResponseDto == null ) {
            return user;
        }

        if ( userResponseDto.getId() != null ) {
            user.setId( userResponseDto.getId() );
        }
        if ( userResponseDto.getFullName() != null ) {
            user.setFullName( userResponseDto.getFullName() );
        }
        if ( userResponseDto.getStaff() != null ) {
            if ( user.getStaff() == null ) {
                user.setStaff( new Staff() );
            }
            staffDtoToStaff1( userResponseDto.getStaff(), user.getStaff() );
        }
        if ( userResponseDto.getEmail() != null ) {
            user.setEmail( userResponseDto.getEmail() );
        }
        if ( user.getRoles() != null ) {
            Set<Role> set = roleDtoSetToRoleSet( userResponseDto.getRoles() );
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDtoSetToRoleSet( userResponseDto.getRoles() );
            if ( set != null ) {
                user.setRoles( set );
            }
        }

        return user;
    }

    @Override
    public User toEntity(CreateUserRequestDto createUserRequestDto) {
        if ( createUserRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( createUserRequestDto.getId() );
        user.setFullName( createUserRequestDto.getFullName() );
        user.setStaff( staffDtoToStaff2( createUserRequestDto.getStaff() ) );
        user.setPwd( createUserRequestDto.getPwd() );
        user.setEmail( createUserRequestDto.getEmail() );
        user.setPhone( createUserRequestDto.getPhone() );
        user.setCreatedBy( createUserRequestDto.getCreatedBy() );
        user.setRoles( roleDtoSetToRoleSet( createUserRequestDto.getRoles() ) );

        return user;
    }

    @Override
    public CreateUserRequestDto toDto2(User user) {
        if ( user == null ) {
            return null;
        }

        Set<RoleDto> roles = null;
        Long id = null;
        String fullName = null;
        CreateUserRequestDto.StaffDto staff = null;
        String pwd = null;
        String email = null;
        String phone = null;
        Long createdBy = null;

        roles = roleSetToRoleDtoSet( user.getRoles() );
        id = user.getId();
        fullName = user.getFullName();
        staff = staffToStaffDto2( user.getStaff() );
        pwd = user.getPwd();
        email = user.getEmail();
        phone = user.getPhone();
        createdBy = user.getCreatedBy();

        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto( id, fullName, staff, pwd, email, phone, createdBy, roles );

        return createUserRequestDto;
    }

    @Override
    public User partialUpdate(CreateUserRequestDto createUserRequestDto, User user) {
        if ( createUserRequestDto == null ) {
            return user;
        }

        if ( createUserRequestDto.getId() != null ) {
            user.setId( createUserRequestDto.getId() );
        }
        if ( createUserRequestDto.getFullName() != null ) {
            user.setFullName( createUserRequestDto.getFullName() );
        }
        if ( createUserRequestDto.getStaff() != null ) {
            if ( user.getStaff() == null ) {
                user.setStaff( new Staff() );
            }
            staffDtoToStaff3( createUserRequestDto.getStaff(), user.getStaff() );
        }
        if ( createUserRequestDto.getPwd() != null ) {
            user.setPwd( createUserRequestDto.getPwd() );
        }
        if ( createUserRequestDto.getEmail() != null ) {
            user.setEmail( createUserRequestDto.getEmail() );
        }
        if ( createUserRequestDto.getPhone() != null ) {
            user.setPhone( createUserRequestDto.getPhone() );
        }
        if ( createUserRequestDto.getCreatedBy() != null ) {
            user.setCreatedBy( createUserRequestDto.getCreatedBy() );
        }
        if ( user.getRoles() != null ) {
            Set<Role> set = roleDtoSetToRoleSet( createUserRequestDto.getRoles() );
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDtoSetToRoleSet( createUserRequestDto.getRoles() );
            if ( set != null ) {
                user.setRoles( set );
            }
        }

        return user;
    }

    protected Staff staffDto1ToStaff(UserDto.StaffDto1 staffDto1) {
        if ( staffDto1 == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setId( staffDto1.getId() );
        staff.setProfession( staffDto1.getProfession() );
        staff.setCompanyRole( staffDto1.getCompanyRole() );

        return staff;
    }

    protected Set<Role> roleDtoSetToRoleSet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleDto roleDto : set ) {
            set1.add( roleMapper.toEntity( roleDto ) );
        }

        return set1;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Role role : set ) {
            set1.add( roleMapper.toDto( role ) );
        }

        return set1;
    }

    protected UserDto.StaffDto1 staffToStaffDto1(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        Long id = null;
        String profession = null;
        String companyRole = null;

        id = staff.getId();
        profession = staff.getProfession();
        companyRole = staff.getCompanyRole();

        UserDto.StaffDto1 staffDto1 = new UserDto.StaffDto1( id, profession, companyRole );

        return staffDto1;
    }

    protected void staffDto1ToStaff1(UserDto.StaffDto1 staffDto1, Staff mappingTarget) {
        if ( staffDto1 == null ) {
            return;
        }

        if ( staffDto1.getId() != null ) {
            mappingTarget.setId( staffDto1.getId() );
        }
        if ( staffDto1.getProfession() != null ) {
            mappingTarget.setProfession( staffDto1.getProfession() );
        }
        if ( staffDto1.getCompanyRole() != null ) {
            mappingTarget.setCompanyRole( staffDto1.getCompanyRole() );
        }
    }

    protected Staff staffDtoToStaff(UserResponseDto.StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setId( staffDto.getId() );
        staff.setProfession( staffDto.getProfession() );
        staff.setCompanyRole( staffDto.getCompanyRole() );

        return staff;
    }

    protected UserResponseDto.StaffDto staffToStaffDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        Long id = null;
        String profession = null;
        String companyRole = null;

        id = staff.getId();
        profession = staff.getProfession();
        companyRole = staff.getCompanyRole();

        UserResponseDto.StaffDto staffDto = new UserResponseDto.StaffDto( id, profession, companyRole );

        return staffDto;
    }

    protected void staffDtoToStaff1(UserResponseDto.StaffDto staffDto, Staff mappingTarget) {
        if ( staffDto == null ) {
            return;
        }

        if ( staffDto.getId() != null ) {
            mappingTarget.setId( staffDto.getId() );
        }
        if ( staffDto.getProfession() != null ) {
            mappingTarget.setProfession( staffDto.getProfession() );
        }
        if ( staffDto.getCompanyRole() != null ) {
            mappingTarget.setCompanyRole( staffDto.getCompanyRole() );
        }
    }

    protected Staff staffDtoToStaff2(CreateUserRequestDto.StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setProfession( staffDto.getProfession() );
        staff.setCompanyRole( staffDto.getCompanyRole() );

        return staff;
    }

    protected CreateUserRequestDto.StaffDto staffToStaffDto2(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        String profession = null;
        String companyRole = null;

        profession = staff.getProfession();
        companyRole = staff.getCompanyRole();

        CreateUserRequestDto.StaffDto staffDto = new CreateUserRequestDto.StaffDto( profession, companyRole );

        return staffDto;
    }

    protected void staffDtoToStaff3(CreateUserRequestDto.StaffDto staffDto, Staff mappingTarget) {
        if ( staffDto == null ) {
            return;
        }

        if ( staffDto.getProfession() != null ) {
            mappingTarget.setProfession( staffDto.getProfession() );
        }
        if ( staffDto.getCompanyRole() != null ) {
            mappingTarget.setCompanyRole( staffDto.getCompanyRole() );
        }
    }
}
