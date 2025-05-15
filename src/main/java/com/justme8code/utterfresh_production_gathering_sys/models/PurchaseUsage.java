package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class PurchaseUsage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private double usableWeightLeft;
    private double totalKgUsed;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Purchase purchase;
}
