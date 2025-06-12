package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.ProductionOverView;

import java.util.List;

public interface ProductionOverviewService {
    /**
     * This method retrieves the production overview,
     * which includes:
     * - Total number of productions
     * - List of productions currently in progress
     * - List of recent productions
     *
     * @return ProductionOverView object containing the overview details
     * This method is typically used to provide a summary of production activities
     * in the system, allowing users to quickly assess the status of ongoing and recent productions.
     */
    ProductionOverView getProductionOverview();

    List<ProdDashboardData> getProductionsInProgress();

    List<ProdDashboardData> getRecentProductions();

    long countTotalProductions();
}
