package com.justme8code.utterfresh_production_gathering_sys.services;

import com.justme8code.utterfresh_production_gathering_sys.models.RecentActivity;
import com.justme8code.utterfresh_production_gathering_sys.repository.RecentActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecentActivityService {

    private final RecentActivityRepository recentActivityRepository;

    public RecentActivityService(RecentActivityRepository recentActivityRepository) {
        this.recentActivityRepository = recentActivityRepository;
    }

    public void addActivity(String activityType, String message) {
        RecentActivity activity = new RecentActivity();
        activity.setActivityType(activityType);
        activity.setMessage(message);
        activity.setTimestamp(LocalDateTime.now());
        activity.setRecent(true);
        recentActivityRepository.save(activity);
    }

    public List<RecentActivity> getRecentActivities() {
        return recentActivityRepository.findByIsRecentTrue();
    }

    public void updateRecentStatus() {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        List<RecentActivity> oldActivities = recentActivityRepository.findByTimestampBefore(threeDaysAgo);
        for (RecentActivity activity : oldActivities) {
            activity.setRecent(false);
        }
        recentActivityRepository.saveAll(oldActivities);
    }
}