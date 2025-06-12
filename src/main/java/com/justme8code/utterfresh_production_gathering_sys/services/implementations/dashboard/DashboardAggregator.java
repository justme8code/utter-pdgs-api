package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.InventoryDashboardData;
import org.springframework.stereotype.Service;

@Service
public class DashboardAggregator {

    private final IngredientUsageOverviewImpl ingredientUsageOverviewImpl;
    private final RawMaterialUsageOverviewImpl rawMaterialUsageOverviewImpl;

    public DashboardAggregator(IngredientUsageOverviewImpl ingredientUsageOverviewImpl,
                               RawMaterialUsageOverviewImpl rawMaterialUsageOverviewImpl) {
        this.ingredientUsageOverviewImpl = ingredientUsageOverviewImpl;
        this.rawMaterialUsageOverviewImpl = rawMaterialUsageOverviewImpl;
    }

    /**
     * Returns inventory dashboard data including:
     * - Total usable liters for ingredients and raw materials
     * - Low stock ingredients/raw materials (with optional thresholds)
     * - Current breakdown of items in store
     *
     * @param ingredientThreshold  Optional custom threshold for ingredients
     * @param rawMaterialThreshold Optional custom threshold for raw materials
     * @return InventoryDashboardData
     */

    public InventoryDashboardData getInventoryDashboard(Double ingredientThreshold, Double rawMaterialThreshold) {
        InventoryDashboardData dashboard = new InventoryDashboardData();

        dashboard.setIngredientTotalLitres(ingredientUsageOverviewImpl.getIngredientTotalUsableLiters());
        dashboard.setRawMaterialTotalLitres(rawMaterialUsageOverviewImpl.getRawMaterialTotalUsableLiters());

        dashboard.setLowStockIngredients(ingredientUsageOverviewImpl.getLowStockIngredients(
                ingredientThreshold != null ? ingredientThreshold : calculateFallbackIngredientThreshold()
        ));

        dashboard.setLowStockRawMaterials(rawMaterialUsageOverviewImpl.getLowStockRawMaterials(
                rawMaterialThreshold != null ? rawMaterialThreshold : calculateFallbackRawThreshold()
        ));

        dashboard.setIngredientBreakdown(ingredientUsageOverviewImpl.getIngredientsInStore());
        dashboard.setRawMaterialBreakdown(rawMaterialUsageOverviewImpl.getIngredientsInStore());

        return dashboard;
    }

    private double calculateFallbackIngredientThreshold() {
        // TODO: make it dynamic later, for now:
        return 5.0;
    }

    private double calculateFallbackRawThreshold() {
        return 7.0;
    }
}
