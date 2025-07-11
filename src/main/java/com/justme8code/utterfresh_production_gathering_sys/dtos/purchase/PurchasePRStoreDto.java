package com.justme8code.utterfresh_production_gathering_sys.dtos.purchase;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionStoreDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePRStoreDto {
    private ProductionStoreDto productionStore;
    private PurchaseDto purchase;
}
