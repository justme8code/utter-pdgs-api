package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product_mixes")
public class ProductMix extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Production production;

    @OneToOne
    private Product product;

    @ManyToMany
    @JoinTable(
            name = "product_mix_ingredients",
            joinColumns = @JoinColumn(name = "product_mix_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @ToString.Exclude
    private List<Ingredient> ingredients;

    private Double litresUsed;
    private Double totalLitersUsed;
    private Integer qty;
    private Double brixOnDiluent;

    private Double initialBrix;
    private Double finalBrix;
    private Double initialPH;
    private Double finalPH;
}
