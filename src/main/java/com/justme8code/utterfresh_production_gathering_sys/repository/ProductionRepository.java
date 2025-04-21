package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductionRepository extends JpaRepository<Production, Long>, JpaSpecificationExecutor<Production> {

    @Query("""
        SELECT new com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo(
            p.createdAt, p.id, p.productionNumber,  p.name, p.status
        )
        FROM Production p
        WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
        """)
    List<ProductionInfo> findProductionsByName(@Param("name") String name);

    @Query("""
        SELECT new com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo(
           p.createdAt, p.id, p.productionNumber,  p.name, p.status
        )
        FROM Production p
        WHERE p.startDate = :startDate
        """)
    List<ProductionInfo> findProductionsByStartDate(@Param("startDate") LocalDate startDate);

    @Query("SELECT COUNT(p) FROM Production p WHERE DATE(p.createdAt) = :date")
    long countByCreatedDate(LocalDate date);

    Optional<Production> findProductionById(long id);
    
}
