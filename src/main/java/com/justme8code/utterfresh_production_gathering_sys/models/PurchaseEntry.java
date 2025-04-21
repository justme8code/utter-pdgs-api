package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raw_material_id")
    @ToString.Exclude
    private RawMaterial rawMaterial;

    // MaterialsToIngredients
    @OneToOne(mappedBy = "purchaseEntry", cascade = CascadeType.ALL, optional = false)
    private MaterialToIngredient materialToIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @ToString.Exclude
    private Supplier supplier;
    private String uom;

    private double qty;
    private double weight;
    private double productionLost;
    private double usable;
    private double cost;
    private double avgCost;
    private double avgWeightPerUOM;
}
