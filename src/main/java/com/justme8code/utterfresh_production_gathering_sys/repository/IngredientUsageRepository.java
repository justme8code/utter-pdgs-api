package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.IngredientUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientUsageRepository extends JpaRepository<IngredientUsage, Long> {
}