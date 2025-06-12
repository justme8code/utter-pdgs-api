package com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialInStore {
    private String rawMaterialName;
    private Double totalQty;
}
