package com.justme8code.utterfresh_production_gathering_sys.models.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurement, Long>, JpaSpecificationExecutor<UnitOfMeasurement> {
}