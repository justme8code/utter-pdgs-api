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

    private String uom;

    @ManyToMany(mappedBy = "rawMaterials", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Ingredient> ingredients;
}
