package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionEvaluationMapper {
    ProductionEvaluation toEntity(ProductionEvaluationDto productionEvaluationDto);

    ProductionEvaluationDto toDto(ProductionEvaluation productionEvaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductionEvaluation partialUpdate(ProductionEvaluationDto productionEvaluationDto, @MappingTarget ProductionEvaluation productionEvaluation);
}