package com.justme8code.utterfresh_production_gathering_sys.models.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.BaseEntity;
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

    private String uom;

    @ManyToMany
    @JoinTable(
            name = "ingredient_raw_material",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "raw_material_id")
    )
    @ToString.Exclude
    private List<RawMaterial> rawMaterials;
}
