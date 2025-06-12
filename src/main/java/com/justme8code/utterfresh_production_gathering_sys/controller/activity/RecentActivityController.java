package com.justme8code.utterfresh_production_gathering_sys.controller.activity;

import com.justme8code.utterfresh_production_gathering_sys.models.RecentActivity;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.activity.RecentActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recent-activities")
public class RecentActivityController {

    private final RecentActivityService recentActivityService;

    public RecentActivityController(RecentActivityService recentActivityService) {
        this.recentActivityService = recentActivityService;
    }

    // Endpoint to retrieve all recent activities
    @GetMapping
    public ResponseEntity<List<RecentActivity>> getRecentActivities() {
        List<RecentActivity> recentActivities = recentActivityService.getRecentActivities();
        return ResponseEntity.ok(recentActivities);
    }

    // Endpoint to add a new activity
    @PostMapping
    public ResponseEntity<RecentActivity> addActivity(
            @RequestParam String activityType,
            @RequestParam String message) {
        recentActivityService.addActivity(activityType, message);
        return ResponseEntity.ok().build();
    }
}