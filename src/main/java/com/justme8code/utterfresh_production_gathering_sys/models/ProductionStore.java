package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "production_store")
public class ProductionStore extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @OneToMany(mappedBy = "productionStore", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<IngredientStore> ingredientStores = new ArrayList<>();

    @OneToMany(mappedBy = "productionStore",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RawMaterialStore> rawMaterialStores = new ArrayList<>();
}
