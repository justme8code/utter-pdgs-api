package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionFullDataDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;

import java.util.List;

public interface ProductionService {

    ProductionDto createProduction(ProductionDto productionDto);

    ProductionDto getProductionById(long id);

    List<ProductionDto> getProductions(int page, int size);

    List<ProductionDto> getProductionsByName(String name);

    List<ProductionDto> getProductionsByStartDate(String startDate);

    void updateProduction(Production production);

    void deleteProduction(Long id);

    List<ProductMixDto> getProductMix(long productId);

    ProductionStoreDto getProductionStoreByProductionId(long productionId);
    ProductionFullDataDto getProductionFullDetails(Long productionId);

}
