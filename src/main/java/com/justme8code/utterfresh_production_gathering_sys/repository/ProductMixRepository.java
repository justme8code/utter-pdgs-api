package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductMixRepository extends JpaRepository<ProductMix, Long>, JpaSpecificationExecutor<ProductMix> {
}