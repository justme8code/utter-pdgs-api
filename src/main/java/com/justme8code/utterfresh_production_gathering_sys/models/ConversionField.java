package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class ConversionField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private double productionLitresLost;
    @Column(updatable = false)

    private double kgUsed;
    private double outPutLitres;
    private double usableLitres;
    @Column(name = "litres_per_kg")
    private double litresPerKg;
    @Column(name = "cost_per_litre")
    private double costPerLitre;
    private double rawBrix;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "conversion_id")
    private Conversion conversion;


}
