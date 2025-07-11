package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto1;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IngredientMapper {
    Ingredient toEntity(IngredientDto ingredientDto);

    IngredientDto toDto(Ingredient ingredient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ingredient partialUpdate(IngredientDto ingredientDto, @MappingTarget Ingredient ingredient);

    Ingredient toEntity(IngredientDto1 ingredientDto1);

    IngredientDto1 toDto1(Ingredient ingredient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ingredient partialUpdate(IngredientDto1 ingredientDto1, @MappingTarget Ingredient ingredient);
}