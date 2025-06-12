package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductMixDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.ProductMixOverview;

import java.util.List;

public interface ProductMixOverviewService {
    //total products by mixes number of mix = products produced
    // top product by mix count
    // recent product mixes

    long totalProductsProduced();

    List<ProductDashboardData> topProductsByMixCount();

    List<ProductMixDashboardData> recentProductMixes();

    ProductMixOverview getProductMixOverview();
}
