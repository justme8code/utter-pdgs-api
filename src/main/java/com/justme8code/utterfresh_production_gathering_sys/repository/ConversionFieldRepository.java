package com.justme8code.utterfresh_production_gathering_sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversionFieldRepository extends JpaRepository<com.justme8code.utterfresh_production_gathering_sys.models.ConversionField, Long>, JpaSpecificationExecutor<com.justme8code.utterfresh_production_gathering_sys.models.ConversionField> {
    @Query("SELECT f FROM ConversionField f " +
            "JOIN f.conversion c " +
            "JOIN c.production p " +
            "WHERE p.id = :productionId " +
            "AND f.ingredient.id IN :ingredientIds")
    List<com.justme8code.utterfresh_production_gathering_sys.models.ConversionField> findFieldsForProductionAndIngredients(
            @Param("productionId") Long productionId,
            @Param("ingredientIds") List<Long> ingredientIds);

}