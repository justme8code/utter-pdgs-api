package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMixIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMixIngredientRepository extends JpaRepository<ProductMixIngredient, Long> {
}