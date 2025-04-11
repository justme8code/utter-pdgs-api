package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.Level1DataInsightDto;
import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import com.justme8code.utterfresh_production_gathering_sys.repository.DynamicDataRepository;
import com.justme8code.utterfresh_production_gathering_sys.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import java.util.*;

@Service
public class DynamicDataInsightService {

    private final DynamicDataRepository dynamicDataRepository;

    public DynamicDataInsightService(DynamicDataRepository dynamicDataRepository) {
        this.dynamicDataRepository = dynamicDataRepository;
    }

    public Level1DataInsightDto getLevel1DataInsight() {
        List<DynamicData> dynamicDataList = dynamicDataRepository.findAll();
        if (!dynamicDataList.isEmpty()) {
            // convert the list to json:
            List<String> jsonData = new ArrayList<>();
            for (DynamicData data : dynamicDataList) {
                try {
                    String json = JsonUtils.toJson(data.getDataMap());
                    jsonData.add(json);
                } catch (Exception e) {
                    // Handle exception
                }
            }
            // Generate insights from the JSON data
            // Return the insights
            return generateLevel1DataInsight(jsonData);
        }
        return null;
    }


    /**
     * This method generates insights from the provided JSON data.
     * It calculates total quantities, costs, and production losses,
     * and identifies the most frequently used raw materials.
     *
     * @param jsonData List of JSON strings representing the data
     * @return Level1DataInsightDto containing the calculated insights
     */
    public Level1DataInsightDto generateLevel1DataInsight(List<String> jsonData){
        int totalQtyOfRawMaterials = 0;
        int totalCostOfPurchases = 0;
        int avgCostOfPurchases = 0;
        int totalProductionLoss = 0;

        Map<String, Integer> topRawMaterialFrequency = new HashMap<>();

        Level1DataInsightDto level1DataInsightDto = new Level1DataInsightDto();
        for (String json : jsonData) {
            try {
                Map<String, Object> dataMap = JsonUtils.fromJson(json);
                if (dataMap.get("populateEditPurchases") instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> populatedEditPurchases = (List<Map<String, Object>>) dataMap.get("populateEditPurchases");

                    Map<String, Long> populateEditPurchasesInsightData = lookIntoPopulateEditPurchases(populatedEditPurchases);
                    totalQtyOfRawMaterials += populateEditPurchasesInsightData.get("totalQtyOfRawMaterials");
                    totalCostOfPurchases += populateEditPurchasesInsightData.get("totalCostOfPurchases");
                    avgCostOfPurchases += populateEditPurchasesInsightData.get("avgCostOfPurchases");
                    totalProductionLoss += populateEditPurchasesInsightData.get("totalProductionLost");

                    // Track the most-used raw material in this dataset
                    String topRawMaterial = getTopRawMaterialByQty(populatedEditPurchases);
                    if (topRawMaterial != null) {
                        topRawMaterialFrequency.merge(topRawMaterial, 1, Integer::sum);
                    }
                }
            } catch (Exception e) {
                // Log or handle parsing error
            }
        }

        level1DataInsightDto.setTotalQtyOfRawMaterials(totalQtyOfRawMaterials);
        level1DataInsightDto.setTotalCostOfPurchases(totalCostOfPurchases);
        level1DataInsightDto.setAvgCostOfPurchases(avgCostOfPurchases);
        level1DataInsightDto.setTotalProductionLost(totalProductionLoss);

        // Determine most frequently top-used raw material
        String mostUsedRawMaterial = topRawMaterialFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        List<Map<String, Object>> top3RawMaterials = topRawMaterialFrequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("name", entry.getKey());
                    result.put("count", entry.getValue());
                    return result;
                })
                .toList();


        level1DataInsightDto.setTop3PurchasedRawMaterials(top3RawMaterials);


        level1DataInsightDto.setMostPurchasedRawMaterials(mostUsedRawMaterial);

        return level1DataInsightDto;
    }

    public Map<String, Long> lookIntoPopulateEditPurchases(List<Map<String, Object>> dataList){
        int totalQtyOfRawMaterials = 0;
        int totalCostOfPurchases = 0;
        int avgCostOfPurchases = 0;
        int totalProductionLoss = 0;

        for (Map<String, Object> data : dataList) {
            if (data.containsKey("qty")) {
                totalQtyOfRawMaterials += parseIntSafe(data.get("qty"));
            }
            if (data.containsKey("cost")) {
                totalCostOfPurchases += parseIntSafe(data.get("cost"));
            }
            if (data.containsKey("avgCost")) {
                avgCostOfPurchases += parseIntSafe(data.get("avgCost"));
            }
            if (data.containsKey("productionLost")) {
                totalProductionLoss += parseIntSafe(data.get("productionLost"));
            }
        }

        return Map.of(
                "totalQtyOfRawMaterials", (long) totalQtyOfRawMaterials,
                "totalCostOfPurchases", (long) totalCostOfPurchases,
                "avgCostOfPurchases", (long) avgCostOfPurchases,
                "totalProductionLost", (long) totalProductionLoss
        );
    }

    private int parseIntSafe(Object value) {
        if (value instanceof Integer) return (int) value;
        if (value instanceof String) return Integer.parseInt((String) value);
        return 0;
    }

    private String getTopRawMaterialByQty(List<Map<String, Object>> purchases) {
        String topRawMaterial = null;
        int maxQty = Integer.MIN_VALUE;

        for (Map<String, Object> entry : purchases) {
            int qty = parseIntSafe(entry.get("qty"));
            if (qty > maxQty) {
                maxQty = qty;
                topRawMaterial = (String) entry.get("rawMaterials");
            }
        }

        return topRawMaterial;
    }

}
