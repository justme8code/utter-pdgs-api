package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductionStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductionStoreRepository extends JpaRepository<ProductionStore, Long>, JpaSpecificationExecutor<ProductionStore> {
    Optional<ProductionStore> findProductionStoreByProduction_Id(Long productionId);
}