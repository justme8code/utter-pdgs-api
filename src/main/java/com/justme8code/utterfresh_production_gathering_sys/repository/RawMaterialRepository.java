package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long>, JpaSpecificationExecutor<RawMaterial> {
}