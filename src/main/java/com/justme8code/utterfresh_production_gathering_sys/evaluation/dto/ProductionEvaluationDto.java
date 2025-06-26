package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Taste;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductionEvaluation}
 */
@Value
public class ProductionEvaluationDto implements Serializable {
    Long id;
    ProductMixDto productMix;
    Taste taste;
    Taste afterTaste;
    Taste viscosity;
    String comment;
    boolean release;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix}
     */
    @Value
    public static class ProductMixDto implements Serializable {
        Long id;
        String productName;
    }
}