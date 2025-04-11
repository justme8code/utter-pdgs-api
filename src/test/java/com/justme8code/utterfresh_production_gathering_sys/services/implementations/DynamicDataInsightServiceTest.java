package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class DynamicDataInsightServiceTest {

    private DynamicDataInsightService dynamicDataInsightService;

    @BeforeEach
    void setUp() {
        dynamicDataInsightService = new DynamicDataInsightService();
    }

    @Test
    void generateLevel1DataInsight() {
        // Mock input data: JSON strings representing raw materials
        List<String> jsonData = List.of(
                "{\"populateEditPurchases\":[{\"rawMaterials\":\"Pineapple\",\"qty\":\"50\",\"cost\":\"400\",\"avgCost\":\"4\",\"productionLost\":\"20\"}]}",
                "{\"populateEditPurchases\":[{\"rawMaterials\":\"Mango\",\"qty\":\"30\",\"cost\":\"200\",\"avgCost\":\"6\",\"productionLost\":\"10\"}]}",
                "{\"populateEditPurchases\":[{\"rawMaterials\":\"Pineapple\",\"qty\":\"70\",\"cost\":\"500\",\"avgCost\":\"7\",\"productionLost\":\"25\"}]}"
        );

        // Call the method to test
        var result = dynamicDataInsightService.generateLevel1DataInsight(jsonData);

        // Check totals
        assertEquals(150, result.getTotalQtyOfRawMaterials()); // 50 + 30 + 70
        assertEquals(1100, result.getTotalCostOfPurchases());  // 400 + 200 + 500
        assertEquals(17, result.getAvgCostOfPurchases()); // (4 + 6 + 7) / 3
        assertEquals(55, result.getTotalProductionLost()); // 20 + 10 + 25

        // Check the top 3 raw materials
        System.out.println(result.getTop3PurchasedRawMaterials());
        assertNotNull(result.getTop3PurchasedRawMaterials());
        assertEquals(2, result.getTop3PurchasedRawMaterials().size());

        // Check that the first raw material is Pineapple (appears twice, higher qty)
        assertTrue(result.getTop3PurchasedRawMaterials().stream().anyMatch(
                entry -> entry.get("name").equals("Pineapple") && entry.get("count").equals(2)
        ));
    }

    @Test
    void lookIntoPopulateEditPurchases() {
        // Mock input data for populateEditPurchases
        List<Map<String, Object>> dataList = List.of(
                Map.of("rawMaterials", "Pineapple", "qty", 50, "cost", 400, "avgCost", 4, "productionLost", 20),
                Map.of("rawMaterials", "Mango", "qty", 30, "cost", 200, "avgCost", 6, "productionLost", 10),
                Map.of("rawMaterials", "Pineapple", "qty", 70, "cost", 500, "avgCost", 7, "productionLost", 25)
        );

        // Call the method to test
        Map<String, Long> result = dynamicDataInsightService.lookIntoPopulateEditPurchases(dataList);

        // Assert the total quantities, costs, and losses
        assertEquals(150, result.get("totalQtyOfRawMaterials"));
        assertEquals(1100, result.get("totalCostOfPurchases"));
        assertEquals(17, result.get("avgCostOfPurchases"));
        assertEquals(55, result.get("totalProductionLost"));
    }

    @Test
    void lookIntoPopulateEditPurchases_EmptyList() {
        // Test the method with an empty list
        List<Map<String, Object>> emptyList = Collections.emptyList();

        Map<String, Long> result = dynamicDataInsightService.lookIntoPopulateEditPurchases(emptyList);

        // Assert all totals are zero
        assertEquals(0, result.get("totalQtyOfRawMaterials"));
        assertEquals(0, result.get("totalCostOfPurchases"));
        assertEquals(0, result.get("avgCostOfPurchases"));
        assertEquals(0, result.get("totalProductionLoss"));
    }

    @Test
    void generateLevel1DataInsight_EmptyJson() {
        // Test when the jsonData is empty
        List<String> emptyJsonData = Collections.emptyList();

        var result = dynamicDataInsightService.generateLevel1DataInsight(emptyJsonData);

        // Assert all totals are zero
        assertEquals(0, result.getTotalQtyOfRawMaterials());
        assertEquals(0, result.getTotalCostOfPurchases());
        assertEquals(0, result.getAvgCostOfPurchases());
        assertEquals(0, result.getTotalProductionLost());
        assertEquals(0, result.getTop3PurchasedRawMaterials().size()); // No raw materials
    }
}
