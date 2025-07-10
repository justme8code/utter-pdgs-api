package com.justme8code.utterfresh_production_gathering_sys.services.implementations.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.ProductionBatchDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionBatchMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionBatch;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionBatchRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.ProductionBatchHelper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductionBatchService {
    private final ProductionBatchRepository pbRepository;
    private final ProductionBatchMapper productionBatchMapper;
    private final ProductionRepository productionRepository;

    public ProductionBatchService(ProductionBatchRepository pbRepository,
                                  ProductionBatchMapper productionBatchMapper, ProductionRepository productionRepository) {
        this.pbRepository = pbRepository;
        this.productionBatchMapper = productionBatchMapper;
        this.productionRepository = productionRepository;
    }

    public ProductionBatchDto createProductionBatch(long productionId) {
        Production p = productionRepository.findById(productionId).orElseThrow(() -> new EntityNotFoundException("production not found"));
        return productionBatchMapper.toDto(ProductionBatchHelper.createProductionBatch(p, pbRepository));
    }

    // future feature, delete, update: can't do them now cause of inventory back tracking.
    // the system isn't designed fully to be able to like able to update or delete anything yet.
    // that would be hard as so many relationship.
}
