package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductMixRepository extends JpaRepository<ProductMix, Long>, JpaSpecificationExecutor<ProductMix> {
    Optional<ProductMix> findProductMixById(Long id);

    List<ProductMix> findProductMixByProduction_Id(Long productionId);

    @Query("SELECT COUNT(pm) FROM ProductMix pm WHERE DATE(pm.createdAt) = :date")
    long countByCreatedDate(LocalDate date);

    List<ProductMix> findProductMixByProduct_Id(Long productID);

    @Query("SELECT px FROM ProductMix px WHERE px.createdAt BETWEEN :start AND :end ORDER BY px.createdAt DESC")
    List<ProductMix> findTop5ProductMixCreatedAtBetween(@Param("start") LocalDateTime createdAt, @Param("end") LocalDateTime endDate, Pageable pageable);
}