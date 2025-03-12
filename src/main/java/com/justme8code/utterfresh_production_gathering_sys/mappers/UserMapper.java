package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.UserDto2;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "staffCompanyRole", target = "staff.companyRole")
    @Mapping(source = "staffProfession", target = "staff.profession")
    @Mapping(source = "staffId", target = "staff.id")
    User toEntity(UserDto userDto);

    @InheritInverseConfiguration(name = "toEntity")
    UserDto toDto(User user);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    User toEntity(CreateUserRequest createUserRequest);

    CreateUserRequest toDto1(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(CreateUserRequest createUserRequest, @MappingTarget User user);

    User toEntity(UserDto2 userDto2);

    UserDto2 toDto2(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto2 userDto2, @MappingTarget User user);
}