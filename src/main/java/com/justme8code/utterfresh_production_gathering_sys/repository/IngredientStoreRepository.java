package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.IngredientTotalProjection;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientStoreRepository extends JpaRepository<IngredientStore, Long>, JpaSpecificationExecutor<IngredientStore> {

    @Query("SELECT SUM(i.usableLitresLeft) FROM IngredientStore i")
    Double getTotalUsableLitres();

    @Query("SELECT i FROM IngredientStore i WHERE i.usableLitresLeft < :threshold")
    List<IngredientStore> findLowStockIngredients(double threshold);

    @Query("SELECT i.ingredient.name AS ingredientName, SUM(i.usableLitresLeft) AS totalLitres " +
            "FROM IngredientStore i " +
            "GROUP BY i.ingredient.name")
    List<IngredientTotalProjection> getTotalLitresPerIngredient();

}
