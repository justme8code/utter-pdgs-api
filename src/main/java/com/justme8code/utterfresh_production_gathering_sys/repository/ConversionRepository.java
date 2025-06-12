package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConversionRepository extends JpaRepository<Conversion, Long>, JpaSpecificationExecutor<Conversion> {
    @Query("SELECT c FROM Conversion c WHERE c.createdAt BETWEEN :start AND :end ORDER BY c.createdAt DESC")
    List<Production> findTop5ConversionsCreatedAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Pageable pageable);

}