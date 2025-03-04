package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionBatchRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionBatchService;
import org.springframework.stereotype.Service;

@Service
public class ProductionBatchServiceImpl implements ProductionBatchService {
    private final ProductionBatchRepository productionBatchRepository;

    public ProductionBatchServiceImpl(ProductionBatchRepository productionBatchRepository) {
        this.productionBatchRepository = productionBatchRepository;
    }

    @Override
    public void createBatch(ProductionBatch batch) {
        productionBatchRepository.save(batch);
    }

    @Override
    public void updateBatch(ProductionBatch batch) {

    }

    @Override
    public void deleteBatch(ProductionBatch batch) {

    }
}
