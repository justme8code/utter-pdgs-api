package com.justme8code.utterfresh_production_gathering_sys.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Production}
 */
@Value
public class ProductionDetailsDto1 implements Serializable {
    Long id;
    String productionNumber;
    String name;
    Integer lastBatch;
    LocalDate startDate;
    LocalDate endDate;
    StaffDto staff;
    boolean finalized;
    ProductionStoreDto productionStore;
}