package com.justme8code.utterfresh_production_gathering_sys.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Conversion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int batch;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;


    @ManyToOne(optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @OneToMany(mappedBy = "conversion", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ConversionField> fields = new ArrayList<>();
}
