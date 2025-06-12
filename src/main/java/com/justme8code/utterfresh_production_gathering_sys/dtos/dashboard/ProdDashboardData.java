package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.event.Production}
 */
@Value
public class ProdDashboardData implements Serializable {
    Long id;
    String productionNumber;
    String name;
    boolean finalized;
    LocalDate startDate;
    LocalDate endDate;
    StaffDto staff;
}