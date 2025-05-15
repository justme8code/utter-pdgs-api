package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IngredientMapper.class})
public interface RawMaterialMapper {
    RawMaterial toEntity(RawMaterialDto rawMaterialDto);

    RawMaterialDto toDto(RawMaterial rawMaterial);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RawMaterial partialUpdate(RawMaterialDto rawMaterialDto, @MappingTarget RawMaterial rawMaterial);
}