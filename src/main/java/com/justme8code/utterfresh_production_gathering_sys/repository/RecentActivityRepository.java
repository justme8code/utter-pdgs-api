package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.RecentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long>, JpaSpecificationExecutor<RecentActivity> {
    List<RecentActivity> findByIsRecentTrue();
    List<RecentActivity> findByTimestampBefore(LocalDateTime dateTime);
}