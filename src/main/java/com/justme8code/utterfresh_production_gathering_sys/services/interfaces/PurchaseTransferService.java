package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ReturnPurchaseAndTransferDto;

import java.util.List;

public interface PurchaseTransferService {

    List<PurchaseTransferDto> getPurchaseTransfersByProductionId(Long productionId);

    List<PurchaseTransferDto> getAvailablePurchaseTransfers();
    ReturnPurchaseAndTransferDto transferPurchaseToProduction(Long productionId, Long purchaseTransferId);

}
