package com.justme8code.utterfresh_production_gathering_sys.csvrecords;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class PurchaseCsvRecord {

    @CsvBindByPosition(position = 0)
    private Long productionId;

    @CsvBindByPosition(position = 1)
    private String productionNumber;

    @CsvBindByPosition(position = 2)
    private Long purchaseId;

    @CsvBindByPosition(position = 3)
    private String rawMaterialName;

    @CsvBindByPosition(position = 4)
    private String supplierName;

    @CsvBindByPosition(position = 5)
    private double uomQty;

    @CsvBindByPosition(position = 6)
    private double weight;

    @CsvBindByPosition(position = 7)
    private double usableWeight;

    @CsvBindByPosition(position = 8)
    private double productionLostWeight;

    @CsvBindByPosition(position = 9)
    private double cost;

    @CsvBindByPosition(position = 10)
    private double avgCost;

    @CsvBindByPosition(position = 11)
    private double totalKgUsed;

    @CsvBindByPosition(position = 12)
    private double usableWeightLeft;
}