package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffMapper {
    @Mapping(source = "userFullName", target = "user.fullName")
    @Mapping(source = "userId", target = "user.id")
    Staff toEntity(StaffDto staffDto);

    @InheritInverseConfiguration(name = "toEntity")
    StaffDto toDto(Staff staff);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffDto staffDto, @MappingTarget Staff staff);
}