package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PurchaseEntryRepository extends JpaRepository<PurchaseEntry, Long>, JpaSpecificationExecutor<PurchaseEntry> {
     Optional<PurchaseEntry> findPurchaseEntryById(Long id);
}