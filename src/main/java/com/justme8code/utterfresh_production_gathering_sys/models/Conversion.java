package com.justme8code.utterfresh_production_gathering_sys.models;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixIngredientDto;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.*;

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

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
    public static interface ProductMixIngredientMapper {
        @Mapping(source = "ingredientId", target = "ingredient.id")
        ProductMixIngredient toEntity(ProductMixIngredientDto productMixIngredientDto);

        @Mapping(source = "ingredient.id", target = "ingredientId")
        ProductMixIngredientDto toDto(ProductMixIngredient productMixIngredient);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(source = "ingredientId", target = "ingredient.id")
        ProductMixIngredient partialUpdate(ProductMixIngredientDto ingredientUsageDto, @MappingTarget ProductMixIngredient productMixIngredient);
    }
}
