package com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionFullDataDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.PMOutputLessDetail;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixOutputDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.EvaluationDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;

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

    List<ProductMixOutputDto> getProductMixOutput(long productionId);

    List<PMOutputLessDetail> getProductMixOutputLessDetail(long productionId);

    void finalizeProduction(Long productionId);

    List<ProductionDto> getNonFinalizedProductions();

    ProductionStoreDto getProductionStoreByProductionId(long productionId);

    ProductionFullDataDto getProductionFullDetails(Long productionId);

    List<EvaluationDto> getProductionEvaluations(Long productionId);

}
