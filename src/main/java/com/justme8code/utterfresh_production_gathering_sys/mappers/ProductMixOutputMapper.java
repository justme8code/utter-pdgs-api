package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixOutputDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.PMOutputLessDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMixOutputMapper {
    ProductMix toEntity(ProductMixOutputDto productMixOutputDto);

    ProductMixOutputDto toDto(ProductMix productMix);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductMix partialUpdate(ProductMixOutputDto productMixOutputDto, @MappingTarget ProductMix productMix);

    @Mapping(source = "productName", target = "product.name")
    ProductMix toEntity(PMOutputLessDetail PMOutputLessDetail);

    @Mapping(source = "product.name", target = "productName")
    PMOutputLessDetail toDto1(ProductMix productMix);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productName", target = "product.name")
    ProductMix partialUpdate(PMOutputLessDetail PMOutputLessDetail, @MappingTarget ProductMix productMix);
}