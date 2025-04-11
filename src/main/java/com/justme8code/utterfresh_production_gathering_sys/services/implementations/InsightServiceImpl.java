package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.DataGrowthDto;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductMixRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.InsightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsightServiceImpl implements InsightService {
    private final ProductionRepository productionRepository;
    private final ProductMixRepository productMixRepository;

    public InsightServiceImpl(ProductionRepository productionRepository, ProductMixRepository productMixRepository) {
        this.productionRepository = productionRepository;
        this.productMixRepository = productMixRepository;
    }

    @Override
    public List<DataGrowthDto> getDataGrowth(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1)).map(date -> {
            DataGrowthDto dto = new DataGrowthDto();
            dto.setDate(date);
            dto.setNewProductions(productionRepository.countByCreatedDate(date));
            dto.setNewProductMixes(productMixRepository.countByCreatedDate(date));
            return dto;
        }).collect(Collectors.toList());
    }
}