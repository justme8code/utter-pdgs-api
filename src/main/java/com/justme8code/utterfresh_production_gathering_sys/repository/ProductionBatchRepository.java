package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long>, JpaSpecificationExecutor<ProductionBatch> {


}