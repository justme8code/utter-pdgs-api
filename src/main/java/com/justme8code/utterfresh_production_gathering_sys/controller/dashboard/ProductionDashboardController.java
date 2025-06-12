package com.justme8code.utterfresh_production_gathering_sys.controller.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.ProductionOverView;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.dashboard.ProductionOverviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller will handle requests related to the production overview dashboard.
 * It will include methods to fetch total productions, productions in progress, and recent productions.
 * The actual implementation of these methods will depend on the service layer and data access layer.
 */
@RestController
@RequestMapping("/api/dashboard/production-overview")

public class ProductionDashboardController {
    private final ProductionOverviewService productionOverviewService;

    public ProductionDashboardController(ProductionOverviewService productionOverviewService) {
        this.productionOverviewService = productionOverviewService;
    }


    @GetMapping
    public ResponseEntity<ProductionOverView> getProductionOverview() {
        return ResponseEntity.ok(productionOverviewService.getProductionOverview()); // Placeholder implementation
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalProductions() {
        return ResponseEntity.ok(productionOverviewService.countTotalProductions()); // Placeholder implementation
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<ProdDashboardData>> getProductionsInProgress() {
        return ResponseEntity.ok(productionOverviewService.getProductionsInProgress()); // Placeholder implementation
    }

    @GetMapping("/recent")
    public ResponseEntity<List<ProdDashboardData>> getRecentProductions() {
        return ResponseEntity.ok(productionOverviewService.getRecentProductions()); // Placeholder implementation
    }

}
