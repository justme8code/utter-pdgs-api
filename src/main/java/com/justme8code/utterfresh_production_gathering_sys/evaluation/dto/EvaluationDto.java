package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Evaluation}
 */
@Value
public class EvaluationDto implements Serializable {
    Long id;
    LocalDateTime createdAt;
    String batchRange;
    StaffDto staff;
    LocalDate manufacturedDate;
    LocalDate expirationDate;
    EvaluationType evaluationType;


    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.Production}
     */
    @Value
    public static class ProductionDto implements Serializable {
        Long id;
        String name;
        Integer lastBatch;
    }
}