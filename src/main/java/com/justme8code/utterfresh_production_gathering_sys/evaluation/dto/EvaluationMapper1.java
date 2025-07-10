package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.mappers.StaffMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class})
public interface EvaluationMapper1 {
    Evaluation toEntity(EvaluationPayload evaluationPayload);

    @AfterMapping
    default void linkProductionEvaluations(@MappingTarget Evaluation evaluation) {
        evaluation.getProductionEvaluations().forEach(productionEvaluation -> productionEvaluation.setEvaluation(evaluation));
    }

    EvaluationPayload toDto(Evaluation evaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Evaluation partialUpdate(EvaluationPayload evaluationPayload, @MappingTarget Evaluation evaluation);
}