package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Taste;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation}
 */
@Value
public class EvaluationPayload implements Serializable {
    Long id;
    String batchRange;
    StaffDto staff;
    LocalDate manufacturedDate;
    LocalDate expirationDate;
    EvaluationType evaluationType;
    List<ProductionEvaluationDto1> productionEvaluations;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation}
     */
    @Value
    public static class ProductionEvaluationDto1 implements Serializable {
        Long id;
        Long productMixId;
        Taste taste;
        Taste afterTaste;
        Taste viscosity;
        String comment;
        boolean release;
    }
}