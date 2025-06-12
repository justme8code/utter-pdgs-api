package com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Production}
 */
@Value
public class ProductionPayload implements Serializable {
    String name;
    LocalDate startDate;
    LocalDate endDate;
}