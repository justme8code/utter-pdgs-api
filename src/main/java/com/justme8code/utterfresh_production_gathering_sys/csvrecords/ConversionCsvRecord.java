package com.justme8code.utterfresh_production_gathering_sys.csvrecords;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversionCsvRecord {

    // These positions MUST match the order in your CsvExportService's header array

    @CsvBindByPosition(position = 0)
    private Long productionId;

    @CsvBindByPosition(position = 1)
    private String productionNumber;

    @CsvBindByPosition(position = 2)
    private Long conversionId;

    @CsvBindByPosition(position = 3)
    private int batch;

    @CsvBindByPosition(position = 4)
    private Long purchaseId;

    // We need to tell OpenCSV how to format the date/time object
    @CsvBindByPosition(position = 5)
    @CsvDate("yyyy-MM-dd'T'HH:mm:ss") // Matches the format from your test data
    private LocalDateTime conversionCreatedAt;

    @CsvBindByPosition(position = 6)
    private Long fieldId;

    @CsvBindByPosition(position = 7)
    private String ingredientName;

    @CsvBindByPosition(position = 8)
    private double kgUsed;

    @CsvBindByPosition(position = 9)
    private double outPutLitres;

    @CsvBindByPosition(position = 10)
    private double usableLitres;

    @CsvBindByPosition(position = 11)
    private double productionLitresLost;

    @CsvBindByPosition(position = 12)
    private double litresPerKg;

    @CsvBindByPosition(position = 13)
    private double costPerLitre;

    @CsvBindByPosition(position = 14)
    private double rawBrix;
}