package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IngredientUsageMapper.class})
public interface ProductMixMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productionId", target = "production.id")
    ProductMix toEntity(ProductMixDto productMixDto);

    @AfterMapping
    default void linkIngredientUsages(@MappingTarget ProductMix productMix) {
        productMix.getIngredientUsages().forEach(ingredientUsage -> ingredientUsage.setProductMix(productMix));
    }

    @InheritInverseConfiguration(name = "toEntity")
    ProductMixDto toDto(ProductMix productMix);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductMix partialUpdate(ProductMixDto productMixDto, @MappingTarget ProductMix productMix);
}