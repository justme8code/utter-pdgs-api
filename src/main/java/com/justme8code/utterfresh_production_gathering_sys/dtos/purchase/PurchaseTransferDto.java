package com.justme8code.utterfresh_production_gathering_sys.dtos.purchase;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseTransfer;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link PurchaseTransfer}
 */
@Value
public class PurchaseTransferDto implements Serializable {
    Long id;
    PurchaseDto2 purchase;
    Long fromProductionId;
    String fromProductionName;
    boolean transferred;
    String transferNotes;

    /**
     * DTO for {@link Purchase}
     */
    @Value
    public static class PurchaseDto2 implements Serializable {
        Long id;
        String rawMaterialName;
        String rawMaterialUom;
        double cost;
        double purchaseUsageUsableWeightLeft;
        double purchaseUsageTotalKgUsed;
        boolean transferred;
    }
}