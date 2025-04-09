package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientUsageDto;
import com.justme8code.utterfresh_production_gathering_sys.models.IngredientUsage;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IngredientUsageMapper {
    @Mapping(source = "ingredientId", target = "ingredient.id")
    IngredientUsage toEntity(IngredientUsageDto ingredientUsageDto);

    @Mapping(source = "ingredient.id", target = "ingredientId")
    IngredientUsageDto toDto(IngredientUsage ingredientUsage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "ingredientId", target = "ingredient.id")
    IngredientUsage partialUpdate(IngredientUsageDto ingredientUsageDto, @MappingTarget IngredientUsage ingredientUsage);
}