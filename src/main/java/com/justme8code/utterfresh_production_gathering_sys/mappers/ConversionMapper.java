package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionPayload;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConversionMapper {
    @Mapping(source = "purchaseId", target = "purchase.id")
    @Mapping(source = "productionId", target = "production.id")
    Conversion toEntity(ConversionDto conversionDto);

    @AfterMapping
    default void linkFields(@MappingTarget Conversion conversion) {
        conversion.getFields().forEach(field -> field.setConversion(conversion));
    }

    @InheritInverseConfiguration(name = "toEntity")
    ConversionDto toDto(Conversion conversion);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Conversion partialUpdate(ConversionDto conversionDto, @MappingTarget Conversion conversion);

    Conversion toEntity(ConversionPayload ConversionPayload);

    ConversionPayload toDto1(Conversion conversion);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Conversion partialUpdate(ConversionPayload ConversionPayload, @MappingTarget Conversion conversion);
}