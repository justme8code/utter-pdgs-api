package com.justme8code.utterfresh_production_gathering_sys.models.event;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
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

    @OneToMany(
            mappedBy = "productMix",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<ProductMixIngredient> productMixIngredients = new ArrayList<>();

    private Double productCount;
    private Double totalLitersUsed;
    private Integer qty;
    private Double brixOnDiluent;
    private Double initialBrix;
    private Double finalBrix;
    private Double initialPH;
    private Double finalPH;

    @OneToMany(
            mappedBy = "productMix", // <--- ADD THIS
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, // Optional but recommended
            orphanRemoval = true       // Optional but recommended
    )
    @ToString.Exclude
    private List<ProductEvaluation> productEvaluations = new ArrayList<>();


}
