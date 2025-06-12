package com.justme8code.utterfresh_production_gathering_sys.services.implementations.dashboard;

import com.justme8code.utterfresh_production_gathering_sys.repository.ConversionFieldRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ConversionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversionOverviewImpl {
    private final ConversionRepository conversionRepository;
    private final ConversionFieldRepository ConversionFieldRepository;

    public ConversionOverviewImpl(ConversionRepository conversionRepository, ConversionFieldRepository conversionFieldRepository) {
        this.conversionRepository = conversionRepository;
        ConversionFieldRepository = conversionFieldRepository;
    }

    // number of conversions
    public Long numberOfConversions() {
        return conversionRepository.count();
    }
}
