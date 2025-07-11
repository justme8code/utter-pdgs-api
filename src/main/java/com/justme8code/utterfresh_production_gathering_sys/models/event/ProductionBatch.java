package com.justme8code.utterfresh_production_gathering_sys.models.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "production_batches")
public class ProductionBatch extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    @JsonBackReference
    private Production production;

    private Long createdBy;
}
