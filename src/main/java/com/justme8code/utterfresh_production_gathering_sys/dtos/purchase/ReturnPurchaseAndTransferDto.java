package com.justme8code.utterfresh_production_gathering_sys.dtos.purchase;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ReturnPurchaseAndTransferDto {
    PurchaseTransferDto purchaseTransfer;
    PurchaseDto purchase;
}
