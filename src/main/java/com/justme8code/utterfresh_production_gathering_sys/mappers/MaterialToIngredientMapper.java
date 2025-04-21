package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.MaterialToIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.MaterialToIngredient;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialToIngredientMapper {
    MaterialToIngredient toEntity(MaterialToIngredientDto materialToIngredientDto);

    MaterialToIngredientDto toDto(MaterialToIngredient materialToIngredient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MaterialToIngredient partialUpdate(MaterialToIngredientDto materialToIngredientDto, @MappingTarget MaterialToIngredient materialToIngredient);
}