package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.RoleMapper;
import com.justme8code.utterfresh_production_gathering_sys.dtos.users.UserDto;
import com.justme8code.utterfresh_production_gathering_sys.models.users.User;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequestDto;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {RoleMapper.class})
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);

    User toEntity(UserResponseDto userResponseDto);

    UserResponseDto toDto1(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponseDto userResponseDto, @MappingTarget User user);

    User toEntity(CreateUserRequestDto createUserRequestDto);

    CreateUserRequestDto toDto2(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(CreateUserRequestDto createUserRequestDto, @MappingTarget User user);
}