package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMixIngredient;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMixIngredientMapper.class} )
public interface ProductMixIngredientMapper {
    @Mapping(source = "ingredientId", target = "ingredient.id")
    ProductMixIngredient toEntity(ProductMixIngredientDto productMixIngredientDto);

    @Mapping(source = "ingredient.id", target = "ingredientId")
    ProductMixIngredientDto toDto(ProductMixIngredient productMixIngredient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "ingredientId", target = "ingredient.id")
    ProductMixIngredient partialUpdate(ProductMixIngredientDto productMixIngredientDto, @MappingTarget ProductMixIngredient productMixIngredient);
}