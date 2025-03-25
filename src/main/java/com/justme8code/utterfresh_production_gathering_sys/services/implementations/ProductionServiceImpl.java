package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionInfo;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionWithDynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.DynamicData;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.repository.DynamicDataRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ProductionPayload;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.ProductionService;
import com.justme8code.utterfresh_production_gathering_sys.utils.JsonUtils;
import com.justme8code.utterfresh_production_gathering_sys.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final StaffRepository staffRepository;
    private final ProductionMapper productionMapper;
    private final UserRepository userRepository;
    private final DynamicDataRepository dynamicDataRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, StaffRepository staffRepository,
                                 ProductionMapper productionMapper, UserRepository userRepository, DynamicDataRepository dynamicDataRepository)
                           {
        this.productionRepository = productionRepository;
        this.staffRepository = staffRepository;
        this.productionMapper = productionMapper;
        this.userRepository = userRepository;
                               this.dynamicDataRepository = dynamicDataRepository;
                           }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    @Transactional
    public ProductionDto createProduction(ProductionPayload productionPayload) {
        Production production = productionMapper.toEntity(productionPayload);
        production.setStatus(Production.ProductionStatus.RUNNING);

        String staffEmail = SecurityUtils.getCurrentUserId();
        Staff staff = userRepository.findUserByEmail(staffEmail)
                .orElseThrow(() -> new EntityException("Staff not found", HttpStatus.NOT_FOUND))
                .getStaff();

        production.setProductionNumber(productionNumberGenerator());
        production.setStaff(staff);

        DynamicData dynamicData = new DynamicData();
        dynamicData.setName(production.getProductionNumber()); // Set name
        DynamicData retrieved = dynamicDataRepository.save(dynamicData);
        production.setDynamicData(retrieved); // Attach it to production
        Production p = productionRepository.save(production);

        return productionMapper.toDto(p);
    }

    @Override
    public ProductionDto getProductionById(long id) {
    Production production =
        productionRepository.findById(id).orElseThrow(() -> new EntityException("Production not found",HttpStatus.NOT_FOUND));
        return productionMapper.toDto(production);
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
    public ProductionWithDynamicData getProductionWithDynamicData(long id) {
        return productionRepository.findById(id).map(productionMapper::toDto3).orElseThrow(() ->
                new EntityException("Production not found",HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public void createProductionDynamicData(long id, Map<String,Object> data) {
        Production production = productionRepository.findProductionById(id).orElseThrow(() -> new EntityException("Production not found",HttpStatus.NOT_FOUND));
        if(data == null){
            throw new EntityException("Invalid data inputed",HttpStatus.NOT_FOUND);
        }
       try{
           production.getDynamicData().setJsonData(JsonUtils.toJson(data));
       }catch (Exception e){
           throw new EntityException("Could not store data",HttpStatus.BAD_REQUEST);
       }
        productionRepository.save(production);
    }

    @Override
    public void updateProductionDynamicData(long id, Map<String, Object> dynamicData) {
        try{
            String toJson = JsonUtils.toJson(dynamicData);
            Production production = productionRepository.findById(id).orElseThrow(() -> new EntityException("Production not found",HttpStatus.NOT_FOUND));
            production.getDynamicData().setJsonData(toJson);
            productionRepository.save(production);
        }catch (Exception e){
            throw new EntityException("Could not store data",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void setProductionStatus(long productionId,Production.ProductionStatus productionStatus) {
        productionRepository.findById(productionId).orElseThrow(() -> new EntityException("Production does not exist", HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void updateProduction(Production production) {}

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void deleteProduction(Long id) {}

    private String productionNumberGenerator() {
        Random random = new Random();
        String datePart = LocalDateTime.now().toString().replace("-", ""); // e.g., 20250303
        String randomPart = String.format("%04d", random.nextInt(10000)); // Ensures a 4-digit number
        return "PROD-" + datePart + "-" + randomPart;
    }



}
