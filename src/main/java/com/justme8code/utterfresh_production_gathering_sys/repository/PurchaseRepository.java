package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>, JpaSpecificationExecutor<Purchase> {
    Optional<Purchase> findPurchaseById(Long id);

    List<Purchase> findAllByDeletedFalse();

    List<Purchase> findAllByProductionIdAndDeletedFalse(Long productionId);
}