package com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.IngredientInStore;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.RawMaterialInStore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO that represents the inventory dashboard data.
 * <p>
 * Includes total volumes, breakdown of current inventory levels, and low-stock alerts
 * for ingredients and raw materials.
 */
@Getter
@Setter
public class InventoryDashboardData {

    /**
     * Total litres of all ingredients currently in inventory.
     */
    private Double ingredientTotalLitres;

    /**
     * Total quantity of all raw materials currently in inventory (measured in litres or units depending on context).
     */
    private Double rawMaterialTotalLitres;

    /**
     * List of ingredients that are currently below the specified threshold.
     */
    private List<IngredientStoreDashboard> lowStockIngredients;

    /**
     * List of raw materials that are currently below the specified threshold.
     */
    private List<RUDashboardData> lowStockRawMaterials;

    /**
     * Breakdown of total litres available per ingredient currently in inventory.
     * Includes ingredients that may or may not be low on stock.
     */
    private List<IngredientInStore> ingredientBreakdown;

    /**
     * Breakdown of total quantity available per raw material currently in inventory.
     * Includes raw materials that may or may not be low on stock.
     */
    private List<RawMaterialInStore> rawMaterialBreakdown;
}
