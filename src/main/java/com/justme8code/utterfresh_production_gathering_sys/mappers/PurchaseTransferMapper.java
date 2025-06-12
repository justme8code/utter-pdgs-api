package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseTransfer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseTransferMapper {
    @Mapping(source = "fromProductionName", target = "fromProduction.name")
    @Mapping(source = "fromProductionId", target = "fromProduction.id")
    @Mapping(source = "purchase.rawMaterialName", target = "purchase.rawMaterial.name")
    @Mapping(source = "purchase.purchaseUsageTotalKgUsed", target = "purchase.purchaseUsage.totalKgUsed")
    @Mapping(source = "purchase.purchaseUsageUsableWeightLeft", target = "purchase.purchaseUsage.usableWeightLeft")
    @Mapping(source = "purchase.rawMaterialUom", target = "purchase.rawMaterial.uom")
    PurchaseTransfer toEntity(PurchaseTransferDto purchaseTransferDto);

    @InheritInverseConfiguration(name = "toEntity")
    PurchaseTransferDto toDto(PurchaseTransfer purchaseTransfer);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PurchaseTransfer partialUpdate(PurchaseTransferDto purchaseTransferDto, @MappingTarget PurchaseTransfer purchaseTransfer);
}