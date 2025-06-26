package com.justme8code.utterfresh_production_gathering_sys.evaluation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ProductionEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_mix_id", nullable = false)
    @ToString.Exclude
    private ProductMix productMix;

    private Taste taste;

    private Taste afterTaste;

    private Taste viscosity;
    private String comment;

    private boolean release;

}
