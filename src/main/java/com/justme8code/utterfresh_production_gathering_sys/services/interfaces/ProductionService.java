package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;

import java.util.List;
import java.util.Map;

public interface ProductionService {

    ProductionDto createProduction(ProductionPayload productionPayload);
    ProductionDto getProductionById(long id);
    List<ProductionDto> getProductions(int page,int size);
    List<ProductionInfo> getProductionsByName(String name);
    List<ProductionInfo> getProductionsByStartDate(String startDate);
    ProductionWithDynamicData getProductionWithDynamicData(long id);
    void createProductionDynamicData(long productionId,  Map<String,Object> dynamicData);
    void updateProductionDynamicData(long id,  Map<String,Object> dynamicData);
    void setProductionStatus(long productionId,Production.ProductionStatus productionStatus);
    void updateProduction(Production production);
    void deleteProduction(Long id);

}
