package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ConversionField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversionFieldRepository extends JpaRepository<ConversionField, Long>, JpaSpecificationExecutor<ConversionField> {
    @Query("SELECT f FROM ConversionField f " +
            "JOIN f.conversion c " +
            "JOIN c.production p " +
            "WHERE p.id = :productionId " +
            "AND f.ingredient.id IN :ingredientIds")
    List<ConversionField> findFieldsForProductionAndIngredients(
            @Param("productionId") Long productionId,
            @Param("ingredientIds") List<Long> ingredientIds);

    @Query("SELECT SUM(c.kgUsed) FROM ConversionField c ")
    Double totalConversionKgUsed();

    @Query("SELECT SUM(c.outPutLitres) FROM ConversionField c ")
    Double totalLitersProduced();

}