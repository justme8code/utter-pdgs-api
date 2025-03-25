package com.justme8code.utterfresh_production_gathering_sys.res_req_models.response;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductionDtoWithDataResponse {
    private Long id;
    private String productionNumber;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProductionWithDynamicData.StaffDto1 staff;
    private Production.ProductionStatus status;
    private List<ProductionWithDynamicData.ProductionBatchDto> productionBatches;
    private Long dynamicDataId;
    private String dynamicDataName;
    private Map<String,Object> dynamicData;
}
