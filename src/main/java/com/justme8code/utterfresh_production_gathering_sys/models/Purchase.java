package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raw_material_id")
    @ToString.Exclude
    private RawMaterial rawMaterial;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Conversion> conversions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @ToString.Exclude
    private Supplier supplier;

    private double uomQty;
    private double weight;
    private double productionLostWeight;
    private double usableWeight;
    private double cost;
    private double avgCost;
    private double avgWeightPerUOM;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private PurchaseUsage purchaseUsage;

}
