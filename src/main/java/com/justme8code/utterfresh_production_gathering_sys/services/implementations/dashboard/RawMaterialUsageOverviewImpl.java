package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.RUDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.mappers.RawMaterialUsageMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.RawMaterialInStore;
import com.justme8code.utterfresh_production_gathering_sys.repository.PurchaseUsageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawMaterialUsageOverviewImpl {
    private final PurchaseUsageRepository pr;
    private final RawMaterialUsageMapper rawMaterialUsageMapper;

    public RawMaterialUsageOverviewImpl(PurchaseUsageRepository purchaseUsageRepository,
                                        RawMaterialUsageMapper rawMaterialUsageMapper) {
        this.pr = purchaseUsageRepository;
        this.rawMaterialUsageMapper = rawMaterialUsageMapper;
    }

    public Double getRawMaterialTotalUsableLiters() {
        return pr.getTotalQty();
    }

    public List<RawMaterialInStore> getIngredientsInStore() {
        return pr.getQuantityPerRawMaterial().stream()
                .map(rtp -> new RawMaterialInStore(rtp.getRawMaterialName(), rtp.getTotalQuantity()))
                .toList();
    }

    public List<RUDashboardData> getLowStockRawMaterials(double threshold) {
        return pr.findLowStockRaMaterials(threshold)
                .stream()
                .map(rawMaterialUsageMapper::toDto)
                .collect(Collectors.toList());
    }
}
