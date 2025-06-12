package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Taste;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Evaluation}
 */
@Value
public class EvaluationPayload implements Serializable {
    LocalDate manufacturedDate;
    LocalDate expirationDate;
    EvaluationType evaluationType;
    List<ProductEvaluationDto1> productEvaluations;

    /**
     * DTO for {@link ProductEvaluation}
     */
    @Value
    public static class ProductEvaluationDto1 implements Serializable {
        Long productMixId;
        Taste taste;
        Taste afterTaste;
        String viscosity;
        String comment;
        boolean release;
    }
}