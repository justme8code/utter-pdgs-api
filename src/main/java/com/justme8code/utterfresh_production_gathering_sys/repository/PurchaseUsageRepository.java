package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.dashboard.inventory_usage.RawMaterialTotalProjection;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseUsageRepository extends JpaRepository<PurchaseUsage, Long>, JpaSpecificationExecutor<PurchaseUsage> {


    @Query("SELECT SUM(p.usableWeightLeft) FROM PurchaseUsage p")
    Double getTotalQty();

    @Query("SELECT p FROM PurchaseUsage p WHERE p.usableWeightLeft < :threshold")
    List<PurchaseUsage> findLowStockRaMaterials(double threshold);


    @Query("SELECT p.purchase.rawMaterial.name AS rawMaterialName, SUM(p.usableWeightLeft) AS totalQuantity" +
            " FROM PurchaseUsage p " +
            "GROUP BY p.purchase.rawMaterial.name")
    List<RawMaterialTotalProjection> getQuantityPerRawMaterial();

}