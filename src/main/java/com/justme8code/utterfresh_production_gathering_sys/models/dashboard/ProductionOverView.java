package com.justme8code.utterfresh_production_gathering_sys.models.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionOverView {
    private Long totalProductions;
    private List<ProdDashboardData> productionsInProgress;
    private List<ProdDashboardData> recentProductions;
}
