package com.justme8code.utterfresh_production_gathering_sys.models.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class RawMaterialStore {
    @ManyToOne(optional = false)
    @JoinColumn(name = "production_store_id")
    ProductionStore productionStore;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "rawMaterial_id", nullable = false)
    private RawMaterial rawMaterial;
    private double totalUsableQuantity;
    private double totalUsedQuantity;
}
