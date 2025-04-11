package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.DataGrowthDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.Level1DataInsightDto;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.DynamicDataInsightService;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.InsightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/insights")
public class InsightController {
    private final InsightService insightService;
    private final DynamicDataInsightService dynamicDataInsightService;

    public InsightController(InsightService insightService, DynamicDataInsightService dynamicDataInsightService) {
        this.insightService = insightService;
        this.dynamicDataInsightService = dynamicDataInsightService;
    }

    @GetMapping("/data-growth")
    public List<DataGrowthDto> getDataGrowth(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return insightService.getDataGrowth(startDate, endDate);
    }

    @GetMapping("/level1-data")
    // it should return a Level1DataInsightDto
    public ResponseEntity<Level1DataInsightDto> getLevel1Data() {
        Level1DataInsightDto level1DataInsightDto = dynamicDataInsightService.getLevel1DataInsight();
        if (level1DataInsightDto != null) {
            return ResponseEntity.ok(level1DataInsightDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}