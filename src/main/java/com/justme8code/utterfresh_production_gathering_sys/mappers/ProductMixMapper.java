package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMixMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productionId", target = "production.id")
    ProductMix toEntity(ProductMixDto productMixDto);

    ProductMixDto toDto(ProductMix productMix);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductMix partialUpdate(ProductMixDto productMixDto, @MappingTarget ProductMix productMix);
}