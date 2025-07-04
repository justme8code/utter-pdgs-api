package com.justme8code.utterfresh_production_gathering_sys.csvrecords;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionFullDataDto;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CsvExportService {

    public String generatePurchasesCsv(ProductionFullDataDto data) {
        List<PurchaseCsvRecord> records = mapPurchasesToRecords(data);

        String[] header = {
                "Production ID", "Production Number", "Purchase ID", "Raw Material", "Supplier",
                "UOM Quantity", "Total Weight (kg)", "Usable Weight (kg)", "Production Lost Weight (kg)",
                "Total Cost", "Avg Cost per Kg", "Total Kg Used", "Usable Weight Left (kg)"
        };

        return writeCsv(header, records);
    }

    // You would create a similar method for conversions
    public String generateConversionsCsv(ProductionFullDataDto data) {
        // 1. Map DTO to ConversionCsvRecord list
        List<ConversionCsvRecord> records = mapConversionsToRecords(data); // Assume this helper exists

        // 2. Define the header for conversions
        String[] header = {
                "Production ID", "Production Number", "Conversion ID", "Batch", "Purchase ID (Source)",
                "Conversion Timestamp", "Field ID", "Ingredient", "Kg Used", "Output Litres",
                "Usable Litres", "Production Litres Lost", "Litres per Kg", "Cost per Litre", "Raw Brix"
        };

        // 3. Write the CSV
        return writeCsv(header, records);
    }

    /**
     * A generic helper method to write a list of any bean to a CSV string.
     * @param records The list of beans to write.
     * @return A string in CSV format.
     * @param <T> The type of the bean.
     */
    private <T> String writeBeansToCsv(List<T> records) {
        if (records == null || records.isEmpty()) {
            return "";
        }

        try (StringWriter writer = new StringWriter()) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false) // Optional: only quote when needed
                    .build();

            beanToCsv.write(records);

            return writer.toString();

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            // In a real app, log this error properly
            throw new RuntimeException("Failed to generate CSV data", e);
        }
    }



    /**
     * A generic, rock-solid method to write a list of beans to a CSV string
     * with a custom header and fixed column order.
     *
     * @param header The string array for the header row.
     * @param records The list of beans to write (must be annotated with @CsvBindByPosition).
     * @param <T> The type of the bean.
     * @return A string containing the data in CSV format.
     */
    private <T> String writeCsv(String[] header, List<T> records) {
        if (records == null || records.isEmpty()) {
            return "";
        }

        try (StringWriter writer = new StringWriter()) {
            // 👇 Manually write the header line
            writer.write(String.join(",", header));
            writer.write(System.lineSeparator()); // important!

            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType((Class<? extends T>) records.get(0).getClass());
            strategy.setColumnMapping(header); // just sets order, not actual header text

            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .withApplyQuotesToAll(false)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            beanToCsv.write(records);

            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to write CSV data: " + e.getMessage(), e);
        }
    }



    // Helper methods to keep the public methods clean
    private List<PurchaseCsvRecord> mapPurchasesToRecords(ProductionFullDataDto data) {
        List<PurchaseCsvRecord> records = new ArrayList<>();
        Long productionId = data.getProduction().getId();
        String productionNumber = data.getProduction().getProductionNumber();
        data.getPurchases().forEach(purchaseDto -> {
            PurchaseCsvRecord record = new PurchaseCsvRecord();
            record.setProductionId(productionId);
            record.setProductionNumber(productionNumber);
            record.setPurchaseId(purchaseDto.getId());
            record.setRawMaterialName(purchaseDto.getRawMaterial().getName());
            record.setSupplierName(purchaseDto.getSupplier().getFullName());
            record.setUomQty(purchaseDto.getUomQty());
            record.setWeight(purchaseDto.getWeight());
            record.setUsableWeight(purchaseDto.getUsableWeight());
            record.setProductionLostWeight(purchaseDto.getProductionLostWeight());
            record.setCost(purchaseDto.getCost());
            record.setAvgCost(purchaseDto.getAvgCost());
            if (purchaseDto.getPurchaseUsage() != null) {
                record.setTotalKgUsed(purchaseDto.getPurchaseUsage().getTotalKgUsed());
                record.setUsableWeightLeft(purchaseDto.getPurchaseUsage().getUsableWeightLeft());
            }
            records.add(record);
        });

        return records;
    }

    private List<ConversionCsvRecord> mapConversionsToRecords(ProductionFullDataDto data) {

        List<ConversionCsvRecord> records = new ArrayList<>();

        Long productionId = data.getProduction().getId();
        String productionNumber = data.getProduction().getProductionNumber();

        // **This is the core flattening logic**
        // Outer loop: iterate through each Conversion
        data.getConversions().forEach(conversionDto -> {
            // Inner loop: iterate through each ConversionField within the Conversion
            conversionDto.getFields().forEach(fieldDto -> {
                ConversionCsvRecord record = new ConversionCsvRecord();
                // --- Set parent Production data ---
                record.setProductionId(productionId);
                record.setProductionNumber(productionNumber);

                // --- Set parent Conversion data (repeated for each field) ---
                record.setConversionId(conversionDto.getId());
                record.setBatch(conversionDto.getBatch());
                record.setPurchaseId(conversionDto.getPurchaseId());
                record.setConversionCreatedAt(conversionDto.getCreatedAt());

                // --- Set the specific ConversionField data ---
                record.setFieldId(fieldDto.getId());
                record.setIngredientName(fieldDto.getIngredient().getName());
                record.setKgUsed(fieldDto.getKgUsed());
                record.setOutPutLitres(fieldDto.getOutPutLitres());
                record.setUsableLitres(fieldDto.getUsableLitres());
                record.setProductionLitresLost(fieldDto.getProductionLitresLost());
                record.setLitresPerKg(fieldDto.getLitresPerKg());
                record.setCostPerLitre(fieldDto.getCostPerLitre());
                record.setRawBrix(fieldDto.getRawBrix());

                records.add(record);
            });
        });

        return records;
    }


    /**
     * Generates a complete production report by packaging multiple CSVs into a single ZIP archive.
     *
     * @param data The full production data DTO.
     * @return A byte array representing the ZIP file.
     */
    public byte[] generateProductionReportAsZip(ProductionFullDataDto data) {
        String purchasesCsv = generatePurchasesCsv(data);
        String conversionsCsv = generateConversionsCsv(data);

        // Declare the output stream outside the try block so we can return it later.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Use try-with-resources ONLY for the stream that needs closing logic.
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {

            // Create the first entry for purchases.csv
            ZipEntry purchaseEntry = new ZipEntry("purchases.csv");
            zipOutputStream.putNextEntry(purchaseEntry);
            zipOutputStream.write(purchasesCsv.getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();

            // Create the second entry for conversions.csv
            ZipEntry conversionEntry = new ZipEntry("conversions.csv");
            zipOutputStream.putNextEntry(conversionEntry);
            zipOutputStream.write(conversionsCsv.getBytes(StandardCharsets.UTF_8));
            zipOutputStream.closeEntry();

            // When this 'try' block ends, Java will automatically call zipOutputStream.close().
            // zipOutputStream.close() WILL write the final ZIP metadata to the byteArrayOutputStream.

        } catch (IOException e) {
            throw new RuntimeException("Failed to generate ZIP file", e);
        }

        // NOW that the ZipOutputStream is closed and the data is finalized,
        // we can safely get the bytes and return them.
        return byteArrayOutputStream.toByteArray();
    }


    /**
     * Generates a single CSV report for all purchases.
     *
     * @param data The full production data DTO.
     * @return A byte array representing the Purchases CSV file.
     */
    public byte[] generatePurchasesReport(ProductionFullDataDto data) {
        // 1. Call your existing private method to get the CSV string
        String purchasesCsv = generatePurchasesCsv(data);

        // 2. Convert the string to a byte array using a standard encoding
        return purchasesCsv.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Generates a single CSV report for all conversions.
     *
     * @param data The full production data DTO.
     * @return A byte array representing the Conversions CSV file.
     */
    public byte[] generateConversionsReport(ProductionFullDataDto data) {
        // 1. Call your existing private method to get the CSV string
        String conversionsCsv = generateConversionsCsv(data);

        // 2. Convert the string to a byte array
        return conversionsCsv.getBytes(StandardCharsets.UTF_8);
    }
}