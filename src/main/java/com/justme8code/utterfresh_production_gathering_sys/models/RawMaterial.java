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
@Table(name = "raw_materials")
public class RawMaterial extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "raw_material_ingredients",
            joinColumns = @JoinColumn(name = "raw_material_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @ToString.Exclude
    private List<Ingredient> ingredients;
}
