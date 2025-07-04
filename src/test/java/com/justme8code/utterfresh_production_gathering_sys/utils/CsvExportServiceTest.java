package com.justme8code.utterfresh_production_gathering_sys.utils;

import com.justme8code.utterfresh_production_gathering_sys.csvrecords.CsvExportService;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDetailsDto1;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionFullDataDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// REMOVED: Spring Boot specific imports are no longer needed
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// REMOVED: @SpringBootTest is no longer needed
class CsvExportServiceTest {

    // REMOVED: @Autowired private CsvExportService csvExportService;
    // ADDED: Manually instantiate the class we are testing
    private CsvExportService csvExportService;

    private ProductionFullDataDto testData;

    @BeforeEach
    void setUp() {
        // ADDED: Initialize the service before each test
        csvExportService = new CsvExportService();

        // --- ARRANGE: Create a complex, nested test data object (This part is unchanged) ---

        // 1. Production Details
        ProductionDetailsDto1 productionDetails = new ProductionDetailsDto1(
                1L, "PROD-2024-001", "Apple Juice Production", 5,
                LocalDate.of(2024, 1, 1), null, null, false, null
        );

        // 2. Purchases
        PurchaseDto.SupplierDto1 supplier1 = new PurchaseDto.SupplierDto1(50L, "Farm Fresh Apples");
        PurchaseDto.RawMaterialDto1 rawMaterial1 = new PurchaseDto.RawMaterialDto1(100L, "Granny Smith", "Bags");
        PurchaseDto.PurchaseUsageDto1 usage1 = new PurchaseDto.PurchaseUsageDto1(200L, 50.0, 150.0);

        PurchaseDto purchase1 = new PurchaseDto(
                10L, 5.0, 20, 200.0, 195.0, 1000.0, 5.13, 10.0,
                false, rawMaterial1, supplier1, usage1
        );

        PurchaseDto.SupplierDto1 supplier2 = new PurchaseDto.SupplierDto1(51L, "Orchard Grove");
        PurchaseDto.RawMaterialDto1 rawMaterial2 = new PurchaseDto.RawMaterialDto1(101L, "Gala", "Bags");
        PurchaseDto.PurchaseUsageDto1 usage2 = new PurchaseDto.PurchaseUsageDto1(201L, 90.0, 10.0);

        PurchaseDto purchase2 = new PurchaseDto(
                11L, 2.0, 10, 100.0, 98.0, 600.0, 6.12, 10.0,
                false, rawMaterial2, supplier2, usage2
        );

        List<PurchaseDto> purchases = List.of(purchase1, purchase2);

        // 3. Conversions (with nested fields)
        IngredientDto ingredient1 = new IngredientDto(300L, "Pure Juice", "L");
        IngredientDto ingredient2 = new IngredientDto(301L, "Pulp", "kg");

        ConversionDto.ConversionFieldDto field1 = new ConversionDto.ConversionFieldDto(
                400L, 2.5, 150.0, 70.0, 67.5, 0.45, 9.80, 12.5, ingredient1
        );
        ConversionDto.ConversionFieldDto field2 = new ConversionDto.ConversionFieldDto(
                401L, 0.0, 150.0, 10.0, 10.0, 0.06, 1.50, 0.0, ingredient2
        );

        ConversionDto conversion1 = new ConversionDto(
                20L, 1, 1L, 10L, List.of(field1, field2), LocalDateTime.of(2024, 1, 2, 10, 30, 0)
        );

        ConversionDto.ConversionFieldDto field3 = new ConversionDto.ConversionFieldDto(
                402L, 1.0, 90.0, 45.0, 44.0, 0.49, 10.50, 11.8, ingredient1
        );
        ConversionDto conversion2 = new ConversionDto(
                21L, 2, 1L, 11L, List.of(field3), LocalDateTime.of(2024, 1, 3, 11, 0, 0)
        );

        List<ConversionDto> conversions = List.of(conversion1, conversion2);

        testData = new ProductionFullDataDto(productionDetails, purchases, conversions);
    }

    @Test
    @DisplayName("generatePurchasesCsv should return correctly formatted CSV for a list of purchases")
    void generatePurchasesCsv_whenGivenFullData_thenReturnsCorrectCsvString() {
        // --- ACT ---
        String actualCsv = csvExportService.generatePurchasesCsv(testData);

        // --- ASSERT ---
        // NOTE: The order here must match the positions in your PurchaseCsvRecord.java
        String expectedHeader = "Production ID,Production Number,Purchase ID,Raw Material,Supplier,UOM Quantity,Total Weight (kg),Usable Weight (kg),Production Lost Weight (kg),Total Cost,Avg Cost per Kg,Total Kg Used,Usable Weight Left (kg)";
        String expectedRow1 = "1,PROD-2024-001,10,Granny Smith,Farm Fresh Apples,20.0,200.0,195.0,5.0,1000.0,5.13,150.0,50.0";
        String expectedRow2 = "1,PROD-2024-001,11,Gala,Orchard Grove,10.0,100.0,98.0,2.0,600.0,6.12,10.0,90.0";

        String expectedCsv = String.join(System.lineSeparator(), expectedHeader, expectedRow1, expectedRow2) + System.lineSeparator();

        assertEquals(expectedCsv, actualCsv);
    }

    @Test
    @DisplayName("generateConversionsCsv should flatten nested fields into separate CSV rows")
    void generateConversionsCsv_whenGivenNestedData_thenReturnsCorrectlyFlattenedCsv() {
        // --- ACT ---
        String actualCsv = csvExportService.generateConversionsCsv(testData);

        // --- ASSERT ---
        String expectedHeader = "Production ID,Production Number,Conversion ID,Batch,Purchase ID (Source),Conversion Timestamp,Field ID,Ingredient,Kg Used,Output Litres,Usable Litres,Production Litres Lost,Litres per Kg,Cost per Litre,Raw Brix";

        String expectedRow1 = "1,PROD-2024-001,20,1,10,2024-01-02T10:30:00,400,Pure Juice,150.0,70.0,67.5,2.5,0.45,9.8,12.5";
        String expectedRow2 = "1,PROD-2024-001,20,1,10,2024-01-02T10:30:00,401,Pulp,150.0,10.0,10.0,0.0,0.06,1.5,0.0";
        String expectedRow3 = "1,PROD-2024-001,21,2,11,2024-01-03T11:00:00,402,Pure Juice,90.0,45.0,44.0,1.0,0.49,10.5,11.8";

        String expectedCsv = String.join(System.lineSeparator(), expectedHeader, expectedRow1, expectedRow2, expectedRow3) + System.lineSeparator();

        // Print for debug
        System.out.println("----- EXPECTED CSV -----");
        System.out.println(expectedCsv);
        System.out.println("----- ACTUAL CSV -----");
        System.out.println(actualCsv);
        System.out.println("------------------------");

        assertEquals(expectedCsv, actualCsv);
    }


    @Test
    @DisplayName("generatePurchasesCsv should return an empty string for empty purchase list")
    void generatePurchasesCsv_whenPurchaseListIsEmpty_thenReturnsEmptyString() {
        // --- ARRANGE ---
        testData.setPurchases(Collections.emptyList());

        // --- ACT ---
        String actualCsv = csvExportService.generatePurchasesCsv(testData);

        // --- ASSERT ---
        assertTrue(actualCsv.isEmpty());
    }

    @Test
    @DisplayName("generateConversionsCsv should return an empty string for empty conversion list")
    void generateConversionsCsv_whenConversionListIsEmpty_thenReturnsEmptyString() {
        // --- ARRANGE ---
        testData.setConversions(Collections.emptyList());

        // --- ACT ---
        String actualCsv = csvExportService.generateConversionsCsv(testData);

        // --- ASSERT ---
        assertTrue(actualCsv.isEmpty());
    }
}