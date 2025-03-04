package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionBatchRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final StaffRepository staffRepository;
    private final ProductionBatchRepository productionBatchRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, StaffRepository staffRepository, ProductionBatchRepository productionBatchRepository) {
        this.productionRepository = productionRepository;
        this.staffRepository = staffRepository;
        this.productionBatchRepository = productionBatchRepository;
    }

    @Override
    @Transactional
    public Production createProduction(Production production,Long staffId) {
        Staff staffExists = staffRepository.findById(staffId).orElseThrow(() -> new UsernameNotFoundException("Staff not found"));

        production.setProductionNumber(productionNumberGenerator());
        production.setStaff(staffExists);

        ProductionBatch newProductionBatch = production.getProductionBatch();
        if (newProductionBatch.getId() == null) {
            newProductionBatch = productionBatchRepository.save(newProductionBatch);
        }

        production.setProductionBatch(newProductionBatch);
        Production savedProduction = productionRepository.save(production);

        newProductionBatch.getProductionList().add(savedProduction);
        productionBatchRepository.save(newProductionBatch);

        return savedProduction;
    }


    @Override
    @Transactional
    public void updateProduction(Production production) {}

    @Override
    @Transactional
    public void deleteProduction(Long id) {}

    private String productionNumberGenerator() {
        Random random = new Random();
        String datePart = java.time.LocalDate.now().toString().replace("-", ""); // e.g., 20250303
        String randomPart = String.format("%04d", random.nextInt(10000)); // Ensures a 4-digit number
        return "PROD-" + datePart + "-" + randomPart;
    }

}
