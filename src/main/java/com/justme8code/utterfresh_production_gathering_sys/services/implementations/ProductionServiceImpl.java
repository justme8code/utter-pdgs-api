package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final StaffRepository staffRepository;
    private final ProductionMapper productionMapper;

    public ProductionServiceImpl(ProductionRepository productionRepository, StaffRepository staffRepository,
                                 ProductionMapper productionMapper) {
        this.productionRepository = productionRepository;
        this.staffRepository = staffRepository;
        this.productionMapper = productionMapper;
    }

    @Override
    @Transactional
    public ProductionDto createProduction(Production production, Long staffId) {
        Staff staffExists = staffRepository.findById(staffId).orElseThrow(() -> new UsernameNotFoundException("Staff not found"));
        production.setProductionNumber(productionNumberGenerator());
        production.setStaff(staffExists);

        Production p = productionRepository.save(production);
        return productionMapper.toDto(p);
    }
    @Override
    public List<ProductionDto> getProductions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Production> productionPage = productionRepository.findAll(pageable);
        return productionPage.getContent().stream().distinct().map(productionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductionInfo> getProductionsByName(String name) {
        return productionRepository.findProductionsByName(name);
    }

    @Override
    public List<ProductionInfo> getProductionsByStartDate(String startDate) {
        return productionRepository.findProductionsByStartDate(LocalDate.parse(startDate));
    }
    @Override
    @Transactional
    public void updateProduction(Production production) {}

    @Override
    @Transactional
    public void deleteProduction(Long id) {}

    private String productionNumberGenerator() {
        Random random = new Random();
        String datePart = LocalDateTime.now().toString().replace("-", ""); // e.g., 20250303
        String randomPart = String.format("%04d", random.nextInt(10000)); // Ensures a 4-digit number
        return "PROD-" + datePart + "-" + randomPart;
    }

}
