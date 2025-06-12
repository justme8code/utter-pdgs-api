package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductionRepository extends JpaRepository<Production, Long>, JpaSpecificationExecutor<Production> {

    @Query("SELECT COUNT(p) FROM Production p WHERE p.createdAt >= :date AND p.createdAt < :datePlusOne")
    long countByCreatedDate(@Param("date") LocalDate date, @Param("datePlusOne") LocalDate datePlusOne);

    Optional<Production> findProductionById(long id);

    List<Production> findProductionsByFinalizedIsFalse();

    @Query("SELECT p FROM Production p WHERE p.createdAt BETWEEN :start AND :end ORDER BY p.createdAt DESC")
    List<Production> findTop5ProductionsCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Pageable pageable);

}
