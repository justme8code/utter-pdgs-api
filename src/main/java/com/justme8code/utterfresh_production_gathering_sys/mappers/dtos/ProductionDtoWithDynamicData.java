package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Production}
 */
@Value
public class ProductionDtoWithDynamicData implements Serializable {
    Long id;
    String productionNumber;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    StaffDto1 staff;
    Production.ProductionStatus status;
    List<ProductionBatch> productionBatches;
    List<DynamicDataDto> dynamicData;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Staff}
     */
    @Value
    public static class StaffDto1 implements Serializable {
        UserDto1 user;

        /**
         * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.User}
         */
        @Value
        public static class UserDto1 implements Serializable {
            Long id;
            String fullName;
        }
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