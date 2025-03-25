package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DynamicDataRepository extends JpaRepository<DynamicData, Long>, JpaSpecificationExecutor<DynamicData> {
    Optional<DynamicData> findByName(String name);
    Optional<DynamicData> findById(long id);
}