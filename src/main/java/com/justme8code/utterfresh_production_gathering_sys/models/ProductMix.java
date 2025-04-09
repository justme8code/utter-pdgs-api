package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @ManyToOne
    private Production production;
    @ManyToOne
    private Product product;
    @OneToMany(mappedBy = "productMix", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<IngredientUsage> ingredientUsages = new ArrayList<>();
    private Double totalLitersUsed;
    private Integer qty;
    private Double brixOnDiluent;

    private Double initialBrix;
    private Double finalBrix;
    private Double initialPH;
    private Double finalPH;

    public void addIngredientUsage(Ingredient ingredient, Double litres) {
        IngredientUsage usage = new IngredientUsage();
        usage.setIngredient(ingredient);
        usage.setLitresUsed(litres);
        usage.setProductMix(this);
        this.ingredientUsages.add(usage);
    }

}
