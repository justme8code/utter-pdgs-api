package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductMixRepository extends JpaRepository<ProductMix, Long>, JpaSpecificationExecutor<ProductMix> {
    Optional<ProductMix> findProductMixById(Long id);

    List<ProductMix> findProductMixByProduction_Id(Long productionId);



    @Query("SELECT COUNT(pm) FROM ProductMix pm WHERE DATE(pm.createdAt) = :date")
    long countByCreatedDate(LocalDate date);

    List<ProductMix> findProductMixByProduct_Id(Long productID);
}