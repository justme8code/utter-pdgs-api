package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Production}
 */
@Value
public class ProductionWithDynamicData implements Serializable {
    Long id;
    String productionNumber;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    StaffDto1 staff;
    Production.ProductionStatus status;
    List<ProductionBatchDto> productionBatches;
    DynamicDataDto dynamicData;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
     */
    @Value
    public static class StaffDto1 implements Serializable {
        Long id;
        UserDto1 user;
    }

    @Value
    public static class UserDto1 implements Serializable {
        Long id;
        String fullName;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch}
     */
    @Value
    public static class ProductionBatchDto implements Serializable {
        Long id;
        String name;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.DynamicData}
     */
    @Value
    public static class DynamicDataDto implements Serializable {
        Long id;
        String name;
        String jsonData;
    }
}