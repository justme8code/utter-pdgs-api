package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixOutputDto;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface ProductMixOutputMapper {
    ProductMix toEntity(ProductMixOutputDto productMixOutputDto);

    ProductMixOutputDto toDto(ProductMix productMix);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)ProductMix partialUpdate(ProductMixOutputDto productMixOutputDto, @MappingTarget ProductMix productMix);
}