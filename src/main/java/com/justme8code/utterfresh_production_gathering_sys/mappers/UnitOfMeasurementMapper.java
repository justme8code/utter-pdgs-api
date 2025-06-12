package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.UnitOfMeasurementDto;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurement;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UnitOfMeasurementMapper {
    UnitOfMeasurement toEntity(UnitOfMeasurementDto unitOfMeasurementDto);

    UnitOfMeasurementDto toDto(UnitOfMeasurement unitOfMeasurement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UnitOfMeasurement partialUpdate(UnitOfMeasurementDto unitOfMeasurementDto, @MappingTarget UnitOfMeasurement unitOfMeasurement);
}