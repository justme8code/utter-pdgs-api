package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.users.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T11:41:32+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public Staff toEntity(StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setUser( staffDtoToUser( staffDto ) );
        staff.setId( staffDto.id() );

        return staff;
    }

    @Override
    public StaffDto toDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        String userFullName = null;
        Long id = null;

        userFullName = staffUserFullName( staff );
        id = staff.getId();

        StaffDto staffDto = new StaffDto( id, userFullName );

        return staffDto;
    }

    @Override
    public Staff partialUpdate(StaffDto staffDto, Staff staff) {
        if ( staffDto == null ) {
            return staff;
        }

        if ( staff.getUser() == null ) {
            staff.setUser( new User() );
        }
        staffDtoToUser1( staffDto, staff.getUser() );
        if ( staffDto.id() != null ) {
            staff.setId( staffDto.id() );
        }

        return staff;
    }

    protected User staffDtoToUser(StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( staffDto.userFullName() );

        return user;
    }

    private String staffUserFullName(Staff staff) {
        User user = staff.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getFullName();
    }

    protected void staffDtoToUser1(StaffDto staffDto, User mappingTarget) {
        if ( staffDto == null ) {
            return;
        }

        if ( staffDto.userFullName() != null ) {
            mappingTarget.setFullName( staffDto.userFullName() );
        }
    }
}
