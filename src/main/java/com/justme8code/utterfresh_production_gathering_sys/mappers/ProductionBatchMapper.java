package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionBatchDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionBatchMapper {
    ProductionBatch toEntity(ProductionBatchDto productionBatchDto);

    ProductionBatchDto toDto(ProductionBatch productionBatch);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductionBatch partialUpdate(ProductionBatchDto productionBatchDto, @MappingTarget ProductionBatch productionBatch);
}