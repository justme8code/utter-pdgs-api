package com.justme8code.utterfresh_production_gathering_sys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "productions")
public class Production extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20, nullable = false, updatable = false)
    private String productionNumber;

    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "batch_production_id", nullable = false)
    @JsonBackReference
    private ProductionBatch productionBatch;


    @OneToOne
    private Staff staff;

    @Enumerated(EnumType.STRING)
    private ProductionStatus status;

    public enum ProductionStatus {
        RUNNING, COMPLETED
    }
}

