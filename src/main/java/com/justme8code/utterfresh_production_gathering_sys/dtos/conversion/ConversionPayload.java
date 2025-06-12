package com.justme8code.utterfresh_production_gathering_sys.dtos.conversion;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ConversionField;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Conversion}
 */
@Value
public class ConversionPayload implements Serializable {
    List<ConversionFieldDto> fields;

    /**
     * DTO for {@link ConversionField}
     */
    @Value
    public static class ConversionFieldDto implements Serializable {
        double productionLitresLost;
        double kgUsed;
        double outPutLitres;
        double usableLitres;
        double litresPerKg;
        double costPerLitre;
        double rawBrix;
    }
}