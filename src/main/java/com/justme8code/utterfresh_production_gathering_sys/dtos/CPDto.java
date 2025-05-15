package com.justme8code.utterfresh_production_gathering_sys.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductionStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CPDto {
    private ProductionStoreDto productionStore;
    private PurchaseDto purchase;
    private ConversionDto conversion;
}
