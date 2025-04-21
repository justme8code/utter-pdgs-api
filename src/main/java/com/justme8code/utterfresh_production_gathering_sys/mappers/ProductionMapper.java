package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDtoNew;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StaffMapper.class})
public interface ProductionMapper {
    Production toEntity(ProductionDto productionDto);

    ProductionDto toDto(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionDto productionDto, @MappingTarget Production production);

    Production toEntity(ProductionInfo productionInfo);

    ProductionInfo toDto1(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionInfo productionInfo, @MappingTarget Production production);

    Production toEntity(ProductionPayload productionPayload);

    ProductionPayload toDto2(Production production);


    Production toEntity(ProductionWithDynamicData productionWithDynamicData);

    @AfterMapping
    default void linkProductionBatches(@MappingTarget Production production) {
        production.getProductionBatches().forEach(productionBatch -> productionBatch.setProduction(production));
    }

    ProductionWithDynamicData toDto3(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionWithDynamicData productionWithDynamicData, @MappingTarget Production production);

    Production toEntity(ProductionDtoNew productionDtoNew);

    @AfterMapping
    default void linkPurchaseEntries(@MappingTarget Production production) {
        production.getPurchaseEntries().forEach(purchaseEntry -> purchaseEntry.setProduction(production));
    }

    @AfterMapping
    default void linkMaterialToIngredients(@MappingTarget Production production) {
        production.getMaterialToIngredients().forEach(materialToIngredient -> materialToIngredient.setProduction(production));
    }

    ProductionDtoNew toDto4(Production production);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Production partialUpdate(ProductionDtoNew productionDtoNew, @MappingTarget Production production);
}
