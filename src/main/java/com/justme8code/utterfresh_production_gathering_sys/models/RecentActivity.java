package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RecentActivity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityType; // e.g., "Production", "ProductMix", "Product", "DynamicData"
    private String message;      // e.g., "Production XYZ modified at 2023-10-01 10:00"
    private LocalDateTime timestamp; // When the activity occurred

    private boolean isRecent; // Indicates if the activity is still recent

    public boolean isRecent() {
        return isRecent;
    }
    public void setRecent(boolean recent) {
        isRecent = recent;
    }
}