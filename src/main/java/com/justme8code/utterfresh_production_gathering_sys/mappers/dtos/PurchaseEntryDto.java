package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry}
 */
@Value
public class PurchaseEntryDto implements Serializable {
    Long id;
    RawMaterialDto1 rawMaterial;
    SupplierDto1 supplier;
    String uom;
    double qty;
    double weight;
    double productionLost;
    double usable;
    double cost;
    double avgCost;
    double avgWeightPerUOM;


    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
     */
    @Value
    public static class RawMaterialDto1 implements Serializable {
        Long id;
        String name;
        String uom;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Supplier}
     */
    @Value
    public static class SupplierDto1 implements Serializable {
        Long id;
        String fullName;
    }
}