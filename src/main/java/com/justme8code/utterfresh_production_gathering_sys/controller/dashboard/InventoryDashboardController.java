package com.justme8code.utterfresh_production_gathering_sys.controller.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.InventoryDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard.DashboardAggregator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/inventory")

public class InventoryDashboardController {
    private final DashboardAggregator dashboardAggregator;

    public InventoryDashboardController(DashboardAggregator dashboardAggregator) {
        this.dashboardAggregator = dashboardAggregator;
    }

    /// Fetches the Inventory dashboard data
    @GetMapping
    public ResponseEntity<InventoryDashboardData> getInventoryDashboard(
            @RequestParam(required = false) Double ingredientThreshold,
            @RequestParam(required = false) Double rawMaterialThreshold) {
        return ResponseEntity.ok(dashboardAggregator.getInventoryDashboard(ingredientThreshold, rawMaterialThreshold));
    }
}
