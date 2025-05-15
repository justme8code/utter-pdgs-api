package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Purchase;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapper {
    Purchase toEntity(PurchaseDto purchaseDto);

    PurchaseDto toDto(Purchase purchase);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Purchase partialUpdate(PurchaseDto purchaseDto, @MappingTarget Purchase purchase);
}