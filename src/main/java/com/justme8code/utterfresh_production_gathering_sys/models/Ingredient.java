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
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "ingredients")
    @ToString.Exclude
    private List<ProductMix> productMixes;

    @ManyToMany(mappedBy = "ingredients")
    @ToString.Exclude
    private List<RawMaterial> rawMaterials;
}
