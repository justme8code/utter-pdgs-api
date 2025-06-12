package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProdDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDetailsDto1;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class, ProductionStoreMapper.class})
public interface ProductionMapper {

    Production toEntity(ProductionDto productionDto);

    ProductionDto toDto(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(@MappingTarget Production production, ProductionDto productionDto);

    Production toEntity(ProductionDetailsDto1 productionDetailsDto1);

    @AfterMapping
    default void linkProductionStore(@MappingTarget Production production) {
        ProductionStore productionStore = production.getProductionStore();
        if (productionStore != null) {
            productionStore.setProduction(production);
        }
    }

    ProductionDetailsDto1 toDto1(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionDetailsDto1 productionDetailsDto1, @MappingTarget Production production);

    Production toEntity(ProdDashboardData prodDashboardData);

    ProdDashboardData toDto2(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProdDashboardData prodDashboardData, @MappingTarget Production production);
}