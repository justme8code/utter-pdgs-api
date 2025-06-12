package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.mappers.StaffMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class, ProductEvaluationMapper.class})
public interface EvaluationMapper {
    Evaluation toEntity(EvaluationDto evaluationDto);

    EvaluationDto toDto(Evaluation evaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Evaluation partialUpdate(EvaluationDto evaluationDto, @MappingTarget Evaluation evaluation);


    Evaluation toEntity(EvaluationPayload evaluationPayload);

    @AfterMapping
    default void linkProductEvaluations(@MappingTarget Evaluation evaluation) {
        evaluation.getProductEvaluations().forEach(productEvaluation -> productEvaluation.setEvaluation(evaluation));
    }


    EvaluationPayload toDto1(Evaluation evaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Evaluation partialUpdate(EvaluationPayload evaluationPayload, @MappingTarget Evaluation evaluation);

    ProductEvaluation toEntity(EvaluationPayload.ProductEvaluationDto1 productEvaluationDto1);

    @Mapping(source = "productionId", target = "production.id")
    Evaluation toEntity(EvaluationInfoDto evaluationInfoDto);

    @Mapping(source = "production.id", target = "productionId")
    EvaluationInfoDto toDto2(Evaluation evaluation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productionId", target = "production.id")
    Evaluation partialUpdate(EvaluationInfoDto evaluationInfoDto, @MappingTarget Evaluation evaluation);
}