package com.justme8code.utterfresh_production_gathering_sys.models.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductMixDashboardData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductMixOverview {
    private long totalProductsProduced;

    private List<ProductDashboardData> topProductsByMixCount;

    private List<ProductMixDashboardData> recentProductMixes;
}
