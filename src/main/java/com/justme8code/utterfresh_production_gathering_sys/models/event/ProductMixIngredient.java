package com.justme8code.utterfresh_production_gathering_sys.models.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ProductMixIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This is the link to the parent mix
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_mix_id") // This will be the FK in this table
    @JsonBackReference
    @ToString.Exclude // Prevents infinite loops in JSON serialization
    private ProductMix productMix;


    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double litresUsed;


}
