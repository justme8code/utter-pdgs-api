package com.justme8code.utterfresh_production_gathering_sys.dtos.purchase;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Supplier;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Purchase}
 */
@Value
public class PurchaseDto implements Serializable {
    Long id;
    double productionLostWeight;
    double uomQty;
    double weight;
    double usableWeight;
    double cost;
    double avgCost;
    double avgWeightPerUOM;
    boolean transferred;
    RawMaterialDto1 rawMaterial;
    SupplierDto1 supplier;
    PurchaseUsageDto1 purchaseUsage;

    /**
     * DTO for {@link RawMaterial}
     */
    @Value
    public static class RawMaterialDto1 implements Serializable {
        Long id;
        String name;
        String uom;
    }

    /**
     * DTO for {@link Supplier}
     */
    @Value
    public static class SupplierDto1 implements Serializable {
        Long id;
        String fullName;
    }

    /**
     * DTO for {@link PurchaseUsage}
     */
    @Value
    public static class PurchaseUsageDto1 implements Serializable {
        Long id;
        double usableWeightLeft;
        double totalKgUsed;
    }
}