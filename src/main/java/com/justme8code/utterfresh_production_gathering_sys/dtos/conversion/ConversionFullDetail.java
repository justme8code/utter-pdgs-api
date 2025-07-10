package com.justme8code.utterfresh_production_gathering_sys.dtos.conversion;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversionFullDetail {
    private ProductionStoreDto productionStore;
    private PurchaseDto purchase;
    private ConversionDto conversion;
}
