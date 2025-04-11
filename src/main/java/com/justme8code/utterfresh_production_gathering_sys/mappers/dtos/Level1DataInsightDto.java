package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Level1DataInsightDto {
    private long totalQtyOfRawMaterials;
    private long totalCostOfPurchases;
    private long avgCostOfPurchases;
    private long totalProductionLost;
    private String mostPurchasedRawMaterials;
    private List<Map<String, Object>> top3PurchasedRawMaterials;
}
