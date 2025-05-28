package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {Conversion.ProductMixIngredientMapper.class})
public interface ProductMixMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productionId", target = "production.id")
    ProductMix toEntity(ProductMixDto productMixDto);

    @InheritInverseConfiguration(name = "toEntity")
    ProductMixDto toDto(ProductMix productMix);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductMix partialUpdate(ProductMixDto productMixDto, @MappingTarget ProductMix productMix);
}