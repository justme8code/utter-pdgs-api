package com.justme8code.utterfresh_production_gathering_sys.configs;

import com.justme8code.utterfresh_production_gathering_sys.services.RecentActivityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecentActivityScheduler {

    private final RecentActivityService recentActivityService;

    public RecentActivityScheduler(RecentActivityService recentActivityService) {
        this.recentActivityService = recentActivityService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void updateRecentStatus() {
        recentActivityService.updateRecentStatus();
    }
}