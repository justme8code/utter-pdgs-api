package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String unitOfMeasure;
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ProductMix> productMixes = new ArrayList<>();

    @Formula("(SELECT COUNT(pm.id) FROM product_mixes pm WHERE pm.product_id = id)")
    private Long totalProductMixCount;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_ingredients",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @ToString.Exclude
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addProductMix(ProductMix mix) {
        productMixes.add(mix);
        mix.setProduct(this);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}