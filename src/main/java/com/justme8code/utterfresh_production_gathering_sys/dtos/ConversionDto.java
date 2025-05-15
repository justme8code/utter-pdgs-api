package com.justme8code.utterfresh_production_gathering_sys.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.ConversionField;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Conversion}
 */
@Value
public class ConversionDto implements Serializable {
    Long id;
    int batch;
    Long productionId;
    Long purchaseId;
    List<ConversionFieldDto> fields;
    LocalDateTime createdAt;


    /**
     * DTO for {@link ConversionField}
     */
    @Value
    public static class ConversionFieldDto implements Serializable {
        Long id;
        double productionLitresLost;
        double kgUsed;
        double outPutLitres;
        double usableLitres;
        double litresPerKg;
        double costPerLitre;
        double rawBrix;
        IngredientDto ingredient;
    }
}