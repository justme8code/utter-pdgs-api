package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;

import java.util.List;

public interface ProductionService {

    ProductionDto createProduction(ProductionPayload productionPayload);
    List<ProductionDto> getProductions(int page,int size);
    List<ProductionInfo> getProductionsByName(String name);
    List<ProductionInfo> getProductionsByStartDate(String startDate);
    void updateProduction(Production production);
    void deleteProduction(Long id);

}
