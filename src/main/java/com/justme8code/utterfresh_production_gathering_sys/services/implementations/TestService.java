package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductMixMapper;
import com.justme8code.utterfresh_production_gathering_sys.repository.ConversionFieldRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    private final ProductionRepository productionRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductMixMapper productMixMapper;
    private final ConversionFieldRepository mTIField;

    public TestService(ProductionRepository productionRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository,
                       ProductMixMapper productMixMapper, ConversionFieldRepository mTIField) {
        this.productionRepository = productionRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
        this.productMixMapper = productMixMapper;
        this.mTIField = mTIField;
    }


}
