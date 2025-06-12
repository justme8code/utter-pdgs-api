package com.justme8code.utterfresh_production_gathering_sys.evaluation;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.*;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductMixRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.helpers.ProductionHelper;
import com.justme8code.utterfresh_production_gathering_sys.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final StaffRepository staffRepository;
    private final EvaluationMapper evaluationMapper;
    private final ProductionRepository productionRepository;
    private final ProductMixRepository productMixRepository;

    public EvaluationService(EvaluationRepository evaluationRepository, StaffRepository staffRepository,
                             EvaluationMapper evaluationMapper, ProductionRepository productionRepository, ProductMixRepository productMixRepository) {
        this.evaluationRepository = evaluationRepository;
        this.staffRepository = staffRepository;
        this.evaluationMapper = evaluationMapper;
        this.productionRepository = productionRepository;
        this.productMixRepository = productMixRepository;
    }



    public void verifyEvaluationAction(Production production, EvaluationType evaluationType){
        if(production.isFinalized() && evaluationType.equals(EvaluationType.IN_PROCESS)){
            throw new EntityException("Production already finalized, Can't create in process sensory.",HttpStatus.FORBIDDEN);
        }

        if(!production.isFinalized() && evaluationType.equals(EvaluationType.POST_PROCESS)){
            throw new EntityException("Production must be finalized before creating a post process sensory.",HttpStatus.FORBIDDEN);
        }

    }
    // Create evaluation;
    @Transactional
    public EvaluationDto createEvaluation(Long productionId,EvaluationPayload payload) {
        // 1. Fetch the main related entity: Production
        Production production = ProductionHelper.findProductionByIdHelper(productionRepository,productionId);
        verifyEvaluationAction(production, payload.getEvaluationType());// verify type of evaluation on this production
        // 2. Fetch the staff member
        Staff staff = staffRepository.findStaffByUser_Email(SecurityUtils.getCurrentUserId());
        if (staff == null) {
            throw new EntityException("Authenticated user is not a valid staff member", HttpStatus.FORBIDDEN);
        }

        // --- OPTIMIZATION START ---

        // 3. Collect all unique ProductMix IDs from the payload
        Set<Long> productMixIds = payload.getProductEvaluations().stream()
                .map(EvaluationPayload.ProductEvaluationDto1::getProductMixId)
                .collect(Collectors.toSet());

        // 4. Fetch all required ProductMix entities in a SINGLE database query
        List<ProductMix> foundMixes = productMixRepository.findAllById(productMixIds);

        // 5. Validate that all requested ProductMix entities were found
        if (foundMixes.size() != productMixIds.size()) {
            Set<Long> foundIds = foundMixes.stream().map(ProductMix::getId).collect(Collectors.toSet());
            productMixIds.removeAll(foundIds); // The remaining IDs are the ones not found
            throw new EntityException("ProductMix entities not found for IDs: " + productMixIds, HttpStatus.NOT_FOUND);
        }

        // 6. Create a Map for efficient, O(1) lookup by ID. No more DB calls in the loop!
        Map<Long, ProductMix> productMixMap = foundMixes.stream()
                .collect(Collectors.toMap(ProductMix::getId, Function.identity()));

        // --- OPTIMIZATION END ---

        // 7. Assemble the parent and child entities
        Evaluation evaluation = evaluationMapper.toEntity(payload);
        evaluation.setProduction(production);
        evaluation.setStaff(staff);
        List<ProductEvaluation> productEvaluations = payload.getProductEvaluations().stream()
                .map(dto -> {
                    // Get the ProductMix from our pre-fetched map (no DB call)
                    ProductMix productMix = productMixMap.get(dto.getProductMixId());
                    ProductEvaluation productEvaluation = evaluationMapper.toEntity(dto);
                    productEvaluation.setProductMix(productMix);
                    productEvaluation.setEvaluation(evaluation);
                    return productEvaluation;
                })
                .collect(Collectors.toList());

        evaluation.setProductEvaluations(productEvaluations);

        // 8. Save the parent. Cascade will save all children.
        return evaluationMapper.toDto(evaluationRepository.save(evaluation));
    }



    public List<String> evaluationTypes() {
        return Arrays.stream(EvaluationType.values())
                .map(Enum::name).toList();
    }

    public List<EvaluationDto> getEvaluations() {
        return evaluationRepository.findAll().stream().map(evaluationMapper::toDto).toList();
    }


    public EvaluationInfoDto getEvaluation(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findEvaluationById(evaluationId)
                .orElseThrow(() -> new EntityException("Evaluation not found", HttpStatus.NOT_FOUND));

        System.out.println(evaluation.getProductEvaluations());
        return evaluationMapper.toDto2(evaluation);
    }
    // Delete evaluation

    // Update evaluation

    public List<EvaluationDto> getEvaluationsByProductionId(Long productionId) {
        return evaluationRepository.findEvaluationByProduction_Id(productionId)
                .stream().map(evaluationMapper::toDto).collect(Collectors.toList());
    }

    // fetch evaluationsBy production id, where produc
}
