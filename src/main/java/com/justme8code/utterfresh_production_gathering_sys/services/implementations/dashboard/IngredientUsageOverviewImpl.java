package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.IngredientStoreDashboard;
import com.justme8code.utterfresh_production_gathering_sys.mappers.IngredientStoreMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.IngredientInStore;
import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.IngredientTotalProjection;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientStoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientUsageOverviewImpl {

    private final IngredientStoreMapper ingredientStoreMapper;
    // get total usable for each ingredient in store
    private final IngredientStoreRepository iRepository;

    public IngredientUsageOverviewImpl(IngredientStoreMapper ingredientStoreMapper, IngredientStoreRepository iRepository) {
        this.ingredientStoreMapper = ingredientStoreMapper;
        this.iRepository = iRepository;
    }

    public Double getIngredientTotalUsableLiters() {
        return iRepository.getTotalUsableLitres();
    }

    public List<IngredientInStore> getIngredientsInStore() {
        List<IngredientTotalProjection> projections = iRepository.getTotalLitresPerIngredient();

        return projections.stream()
                .map(p -> new IngredientInStore(p.getIngredientName(), p.getTotalLitres()))
                .toList();
    }

    public List<IngredientStoreDashboard> getLowStockIngredients(double threshold) {
        return iRepository.findLowStockIngredients(threshold).stream()
                .map(ingredientStoreMapper::toDto).collect(Collectors.toList());
    }

}
