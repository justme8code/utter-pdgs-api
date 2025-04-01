package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.SupplierDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Supplier;
import com.justme8code.utterfresh_production_gathering_sys.models.SupplierDtoPayload;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupplierMapper {
    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDto supplierDto, @MappingTarget Supplier supplier);

    Supplier toEntity(SupplierDtoPayload supplierDtoPayload);

    SupplierDtoPayload toDto1(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Supplier partialUpdate(SupplierDtoPayload supplierDtoPayload, @MappingTarget Supplier supplier);
}