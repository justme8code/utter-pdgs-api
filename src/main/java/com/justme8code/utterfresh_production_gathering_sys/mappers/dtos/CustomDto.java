package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductMix;

import java.util.List;
import java.util.Map;

public class CustomDto {
    Long id;
    Long productionId;
    Long productId;
    Map<Long, ProductMix> productMixMap;
    Double totalLitersUsed;
    Integer qty;
    Double brixOnDiluent;
    Double initialBrix;
    Double finalBrix;
    Double initialPH;
    Double finalPH;
}
