package com.justme8code.utterfresh_production_gathering_sys.models.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
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
public class IngredientStore extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Each ingredient belongs to one store
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private double usableLitresLeft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "production_store_id", nullable = false)
    private ProductionStore productionStore;
}
