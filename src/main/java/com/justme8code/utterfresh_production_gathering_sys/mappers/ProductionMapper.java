package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class})
public interface ProductionMapper {
    Production toEntity(ProductionDto productionDto);

    ProductionDto toDto(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionDto productionDto, @MappingTarget Production production);

    Production toEntity(ProductionInfo productionInfo);

    ProductionInfo toDto1(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionInfo productionInfo, @MappingTarget Production production);
}