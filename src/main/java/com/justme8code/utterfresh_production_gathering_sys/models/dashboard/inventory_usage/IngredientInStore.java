package com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInStore {
    private String ingredientName;
    private Double totalLitres;
}
