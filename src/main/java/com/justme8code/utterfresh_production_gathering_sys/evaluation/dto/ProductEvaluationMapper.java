package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductEvaluation;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductEvaluationMapper {
    ProductEvaluation toEntity(ProductEvaluationDto productEvaluationDto);

    ProductEvaluationDto toDto(ProductEvaluation productEvaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEvaluation partialUpdate(ProductEvaluationDto productEvaluationDto, @MappingTarget ProductEvaluation productEvaluation);
}