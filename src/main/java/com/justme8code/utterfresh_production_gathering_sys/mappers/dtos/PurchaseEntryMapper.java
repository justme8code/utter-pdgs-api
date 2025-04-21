package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseEntryMapper {
    PurchaseEntry toEntity(PurchaseEntryDto purchaseEntryDto);

    PurchaseEntryDto toDto(PurchaseEntry purchaseEntry);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PurchaseEntry partialUpdate(PurchaseEntryDto purchaseEntryDto, @MappingTarget PurchaseEntry purchaseEntry);
}