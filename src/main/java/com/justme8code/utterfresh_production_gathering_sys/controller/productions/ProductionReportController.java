package com.justme8code.utterfresh_production_gathering_sys.controller.productions;

import com.justme8code.utterfresh_production_gathering_sys.csvrecords.CsvExportService;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionFullDataDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production.ProductionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productions")
public class ProductionReportController {

    private final ProductionService productionService;
    private final CsvExportService csvExportService;

    public ProductionReportController(ProductionService productionService, CsvExportService csvExportService) {
        this.productionService = productionService;
        this.csvExportService = csvExportService;
    }

    @GetMapping("/{productionId}/report/download-zip")
    public ResponseEntity<byte[]> downloadProductionReportAsZip(@PathVariable Long productionId) {
        // 1. Get your complex, hierarchical data object
        ProductionFullDataDto fullData = productionService.getProductionFullDetails(productionId);

        byte[] zipData = csvExportService.generateProductionReportAsZip(fullData);

        HttpHeaders headers = new HttpHeaders();


        String fileName = String.format("production-%s-report.zip", fullData.getProduction().getProductionNumber());
        headers.setContentDispositionFormData("attachment", fileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // A generic "binary file" type

        return new ResponseEntity<>(zipData, headers, HttpStatus.OK);
    }


    @GetMapping("/{productionId}/report/purchases")
    public ResponseEntity<byte[]> downloadPurchasesReport(@PathVariable Long productionId) {

        ProductionFullDataDto fullData = productionService.getProductionFullDetails(productionId);

        byte[] csvData = csvExportService.generatePurchasesReport(fullData);


        HttpHeaders headers = new HttpHeaders();
        String fileName = String.format("production-%s-purchases.csv", fullData.getProduction().getProductionNumber());
        headers.setContentDispositionFormData("attachment", fileName);


        headers.setContentType(MediaType.valueOf("text/csv"));

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }


    @GetMapping("/{productionId}/report/conversions")
    public ResponseEntity<byte[]> downloadConversionsReport(@PathVariable Long productionId) {

        ProductionFullDataDto fullData = productionService.getProductionFullDetails(productionId);


        byte[] csvData = csvExportService.generateConversionsReport(fullData);


        HttpHeaders headers = new HttpHeaders();
        String fileName = String.format("production-%s-conversions.csv", fullData.getProduction().getProductionNumber());
        headers.setContentDispositionFormData("attachment", fileName);


        headers.setContentType(MediaType.valueOf("text/csv"));

        return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
    }
}