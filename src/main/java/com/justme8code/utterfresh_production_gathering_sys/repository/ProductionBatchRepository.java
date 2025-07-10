package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long>, JpaSpecificationExecutor<ProductionBatch> {


    List<ProductionBatch> findProductionBatchByProduction_IdAndActiveIsTrue(Long id);

    Optional<ProductionBatch> findByProductionIdAndActiveTrue(Long productionId);
    
    Optional<List<ProductionBatch>> findProductionBatchByProduction_Id(Long productionId);

}