package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.ProductionOverView;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.dashboard.ProductionOverviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionOverViewImpl implements ProductionOverviewService {
    private final ProductionRepository productionRepository;
    private final ProductionMapper productionMapper;

    public ProductionOverViewImpl(ProductionRepository productionRepository,
                                  ProductionMapper productionMapper) {
        this.productionRepository = productionRepository;
        this.productionMapper = productionMapper;
    }

    @Override
    public ProductionOverView getProductionOverview() {
        ProductionOverView productionOverView = new ProductionOverView();
        productionOverView.setProductionsInProgress(getProductionsInProgress());
        productionOverView.setRecentProductions(getRecentProductions());
        productionOverView.setTotalProductions(countTotalProductions());
        return productionOverView;
    }

    @Override
    public List<ProdDashboardData> getProductionsInProgress() {
        // Logic to find productions in progress
        return productionRepository.findProductionsByFinalizedIsFalse()
                .stream().map(productionMapper::toDto2)
                .toList();
    }

    @Override
    public List<ProdDashboardData> getRecentProductions() {
        // Logic to find recent productions
        Pageable topFive = PageRequest.of(0, 5);
        return productionRepository
                .findTop5ProductionsCreatedAtBetween(
                        LocalDateTime.now().minusDays(30),
                        LocalDateTime.now(), topFive)
                .stream().map(productionMapper::toDto2)
                .toList();
    }

    @Override
    public long countTotalProductions() {
        // Logic to count total productions
        return productionRepository.count();
    }
}
