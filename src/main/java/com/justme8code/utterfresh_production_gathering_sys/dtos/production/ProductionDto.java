package com.justme8code.utterfresh_production_gathering_sys.dtos.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Production}
 */
@Value
public class ProductionDto implements Serializable {
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