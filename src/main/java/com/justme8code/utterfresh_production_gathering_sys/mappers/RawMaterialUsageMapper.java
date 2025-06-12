package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.RUDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RawMaterialUsageMapper {
    @Mapping(source = "rawMaterialName", target = "purchase.rawMaterial.name")
    PurchaseUsage toEntity(RUDashboardData RUDashboardData);

    @Mapping(source = "purchase.rawMaterial.name", target = "rawMaterialName")
    RUDashboardData toDto(PurchaseUsage purchaseUsage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "rawMaterialName", target = "purchase.rawMaterial.name")
    PurchaseUsage partialUpdate(RUDashboardData RUDashboardData, @MappingTarget PurchaseUsage purchaseUsage);
}