package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaterialToIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;
    private double totalUsable;
    private double litresLost;
    private int batch;
    private int outPutLitres;
    private double usable;
    @Column(name = "litres_per_kg")
    private double litresPerKg;
    @Column(name = "cost_per_litre")
    private double costPerLitre;
    private double rawBrix;
    @OneToOne(optional = false)
    @JoinColumn(name = "purchase_entry_id", nullable = false, unique = true)
    private PurchaseEntry purchaseEntry;
}
