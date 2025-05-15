package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductionStore;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductionStoreMapper {
    @Mapping(source = "productionId", target = "production.id")
    ProductionStore toEntity(ProductionStoreDto productionStoreDto);

    @AfterMapping
    default void linkIngredientStores(@MappingTarget ProductionStore productionStore) {
        productionStore.getIngredientStores().forEach(ingredientStore -> ingredientStore.setProductionStore(productionStore));
    }

    @Mapping(source = "production.id", target = "productionId")
    ProductionStoreDto toDto(ProductionStore productionStore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productionId", target = "production.id")
    ProductionStore partialUpdate(ProductionStoreDto productionStoreDto, @MappingTarget ProductionStore productionStore);
}