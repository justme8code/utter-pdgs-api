package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.PurchaseTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface PurchaseTransferRepository extends JpaRepository<PurchaseTransfer, Long>, JpaSpecificationExecutor<PurchaseTransfer> {
    List<PurchaseTransfer> findByFromProductionId(Long productionId);
    List<PurchaseTransfer> findByTransferredIsFalse();
    List<PurchaseTransfer> findByTransferredIsFalseAndFromProductionNotNull();
}