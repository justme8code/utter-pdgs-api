package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Evaluation}
 */
@Value
public class EvaluationInfoDto implements Serializable {
    LocalDateTime createdAt;
    Long id;
    Long productionId;
    StaffDto staff;
    LocalDate manufacturedDate;
    EvaluationType evaluationType;
    LocalDate expirationDate;
    List<ProductEvaluationDto> productEvaluations;
}