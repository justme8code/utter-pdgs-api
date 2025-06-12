package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.ReturnPurchaseAndTransferDto;

import java.util.List;

public interface PurchaseTransferService {

    List<PurchaseTransferDto> getPurchaseTransfersByProductionId(Long productionId);

    List<PurchaseTransferDto> getAvailablePurchaseTransfers();

    ReturnPurchaseAndTransferDto transferPurchaseToProduction(Long productionId, Long purchaseTransferId);

}
