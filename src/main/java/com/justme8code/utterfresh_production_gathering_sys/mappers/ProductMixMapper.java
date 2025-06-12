package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductMixDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
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

    @Mapping(source = "productName", target = "product.name")
    ProductMix toEntity(ProductMixDashboardData productMixDashboardData);

    @Mapping(source = "product.name", target = "productName")
    ProductMixDashboardData toDto1(ProductMix productMix);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productName", target = "product.name")
    ProductMix partialUpdate(ProductMixDashboardData productMixDashboardData, @MappingTarget ProductMix productMix);
}