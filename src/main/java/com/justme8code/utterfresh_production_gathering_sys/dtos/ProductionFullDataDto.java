package com.justme8code.utterfresh_production_gathering_sys.dtos;

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
}
