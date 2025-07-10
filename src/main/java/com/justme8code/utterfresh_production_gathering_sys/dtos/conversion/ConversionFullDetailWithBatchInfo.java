package com.justme8code.utterfresh_production_gathering_sys.dtos.conversion;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionBatchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversionFullDetailWithBatchInfo {
    private ProductionBatchDto productionBatch;
    private ConversionFullDetail conversion;
}
