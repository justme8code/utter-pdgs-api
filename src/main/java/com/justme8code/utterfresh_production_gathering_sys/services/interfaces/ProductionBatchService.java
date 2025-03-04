package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch;

public interface ProductionBatchService {

    void createBatch(ProductionBatch batch);
    void updateBatch(ProductionBatch batch);
    void deleteBatch(ProductionBatch batch);
}
