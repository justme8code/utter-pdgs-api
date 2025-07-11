package com.justme8code.utterfresh_production_gathering_sys.dtos.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionFullDataDto {
    private ProductionDetailsDto1 production;
    private List<PurchaseDto> purchases;
    private List<ConversionDto> conversions;
    private List<ProductionBatchWithConversionsDto> conversionsByBatch;
}
