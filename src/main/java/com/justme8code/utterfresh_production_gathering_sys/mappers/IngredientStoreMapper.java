package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.IngredientStoreDashboard;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IngredientStoreMapper {
    IngredientStore toEntity(IngredientStoreDashboard ingredientStoreDashboard);

    IngredientStoreDashboard toDto(IngredientStore ingredientStore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IngredientStore partialUpdate(IngredientStoreDashboard ingredientStoreDashboard, @MappingTarget IngredientStore ingredientStore);
}