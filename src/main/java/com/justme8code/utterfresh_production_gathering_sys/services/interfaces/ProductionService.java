package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;

public interface ProductionService {

    Production createProduction(Production production,Long staffId);
    void updateProduction(Production production);
    void deleteProduction(Long id);

}
