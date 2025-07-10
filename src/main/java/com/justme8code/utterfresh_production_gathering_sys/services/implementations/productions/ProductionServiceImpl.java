package com.justme8code.utterfresh_production_gathering_sys.services.implementations.productions;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.*;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.PMOutputLessDetail;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixOutputDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationRepository;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.EvaluationDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.dto.EvaluationMapper;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.*;
import com.justme8code.utterfresh_production_gathering_sys.models.event.*;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import com.justme8code.utterfresh_production_gathering_sys.repository.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.specifications.ProductionSpecifications;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.production.ProductionService;
import com.justme8code.utterfresh_production_gathering_sys.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;
    private final ProductionMapper productionMapper;
    private final UserRepository userRepository;
    private final ProductMixRepository productMixRepository;
    private final ProductMixMapper productMixMapper;
    private final ProductionStoreMapper productionStoreMapper;
    private final PurchaseMapper purchaseMapper;
    private final ConversionMapper conversionMapper;
    private final ProductMixOutputMapper productMixOutputMapper;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;
    private final ProductionBatchRepository productionBatchRepository;
    private final ProductionStoreRepository productionStoreRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository,
                                 ProductionMapper productionMapper, UserRepository userRepository, ProductMixRepository productMixRepository,
                                 ProductMixMapper productMixMapper,
                                 ProductionStoreMapper productionStoreMapper, PurchaseMapper purchaseMapper, ConversionMapper conversionMapper,
                                 ProductMixOutputMapper productMixOutputMapper, EvaluationRepository evaluationRepository,
                                 EvaluationMapper evaluationMapper, ProductionBatchRepository productionBatchRepository, ProductionStoreRepository productionStoreRepository) {
        this.productionRepository = productionRepository;
        this.productionMapper = productionMapper;
        this.userRepository = userRepository;
        this.productMixRepository = productMixRepository;
        this.productMixMapper = productMixMapper;
        this.productionStoreMapper = productionStoreMapper;
        this.purchaseMapper = purchaseMapper;
        this.conversionMapper = conversionMapper;
        this.productMixOutputMapper = productMixOutputMapper;
        this.evaluationRepository = evaluationRepository;
        this.evaluationMapper = evaluationMapper;
        this.productionBatchRepository = productionBatchRepository;
        this.productionStoreRepository = productionStoreRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    @Transactional
    public ProductionDto createProduction(ProductionDto productionDto) {
        Production production = productionMapper.toEntity(productionDto);

        String staffEmail = SecurityUtils.getCurrentUserId();
        Staff staff = userRepository.findUserByEmail(staffEmail)
                .orElseThrow(() -> new EntityException("Staff not found", HttpStatus.NOT_FOUND))
                .getStaff();

        production.setProductionNumber(productionNumberGenerator());
        production.setStaff(staff);
        production.setFinalized(false);
        ProductionStore productionStore = new ProductionStore();
        productionStore.setProduction(production);
        production.setProductionStore(productionStore);
        Production p = productionRepository.save(production);
        return productionMapper.toDto(p);
    }


    @Override
    public ProductionDto getProductionById(long id) {
        Production production =
                productionRepository.findById(id).orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
        return productionMapper.toDto(production);
    }

    @Override
    public List<ProductionDto> getProductions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Production> productionPage = productionRepository.findProductionByDeletedIsFalse(pageable);
        return productionPage.getContent().stream().distinct().map(productionMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public List<ProductionDto> getProductionsByName(String name) {
        Specification<Production> spec = Specification
                .where(ProductionSpecifications.hasProductionName(name));
        return productionRepository.findAll(spec).stream().map(productionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductionDto> getProductionsByStartDate(String startDate) {
        Specification<Production> spec = Specification
                .where(ProductionSpecifications.hasStartDate(LocalDate.parse(startDate)));
        return productionRepository.findAll(spec).stream().map(productionMapper::toDto).collect(Collectors.toList());
    }


    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void updateProduction(Production production) {
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCTION_MANAGER')")
    public void softDeleteProduction(Long id) {
        Production production = productionRepository.findProductionById(id).orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
       /* production.setDeleted(true);*/
       /* productionRepository.save(production)*/;
        /*finalizeProduction(production.getId());*/
        productionRepository.delete(production);
    }


    public ProductionStoreDto getProductionStoreByProductionId(long productionId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
        return productionStoreMapper.toDto(production.getProductionStore());
    }

    @Override
    public List<ProductMixDto> getProductMix(long productionId) {
        List<ProductMix> mixes = productMixRepository.findProductMixByProduction_Id(productionId);
        if (mixes == null || mixes.isEmpty()) {
            return new ArrayList<>();
        }
        return mixes.stream().map(productMixMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductMixOutputDto> getProductMixOutput(long productionId) {
        return productMixRepository.findProductMixByProduction_Id(productionId)
                .stream().map(productMixOutputMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PMOutputLessDetail> getProductMixOutputLessDetail(long productionId) {
        return productMixRepository.findProductMixByProduction_Id(productionId)
                .stream().map(productMixOutputMapper::toDto1).toList();
    }


    @Override
    public ProductionFullDataDto getProductionFullDetails(Long productionId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));

        // Separate entities into their own various lists
        List<Purchase> purchases = production.getPurchaseEntries();
        List<Conversion> material = purchases.stream()
                .map(Purchase::getConversions)
                .flatMap(Collection::stream)
                .toList();

        ProductionDetailsDto1 prdto = productionMapper.toDto1(production);

        List<PurchaseDto> peDtos = purchases.stream()
                .map(purchaseMapper::toDto).toList();

        List<ConversionDto> mtiDtos = material.stream()
                .map(conversionMapper::toDto).toList();

        return new ProductionFullDataDto(prdto, peDtos, mtiDtos, null);
    }


    @Override
    public ProductionFullDataDto getProductionFullDetails2(Long productionId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));

        List<Purchase> purchases = production.getPurchaseEntries();

        List<Conversion> allConversions = purchases.stream()
                .map(Purchase::getConversions)
                .flatMap(Collection::stream)
                .toList();

        // Fetch all batches for this production
        List<ProductionBatch> batches = productionBatchRepository.findProductionBatchByProduction_Id(productionId)
                .orElse(new ArrayList<>());

        // Find the active batch (if any)
        Long activeBatchId = batches.stream()
                .filter(ProductionBatch::isActive)
                .findFirst()
                .map(ProductionBatch::getId)
                .orElse(null);

        // Group conversions by batch
        Map<ProductionBatch, List<Conversion>> batchGroupedConversions = allConversions.stream()
                .filter(conversion -> conversion.getBatch() != null)
                .collect(Collectors.groupingBy(Conversion::getBatch));

        // Go through ALL batches, even ones with no conversions
        List<ProductionBatchWithConversionsDto> conversionsByBatch = batches.stream()
                .map(batch -> {
                    List<ConversionDto> conversionDtos = batchGroupedConversions
                            .getOrDefault(batch, Collections.emptyList())
                            .stream()
                            .map(conversionMapper::toDto)
                            .toList();

                    return new ProductionBatchWithConversionsDto(
                            batch.getId(),
                            batch.getName(),
                            Objects.equals(batch.getId(), activeBatchId),
                            conversionDtos
                    );
                })
                .toList();

        ProductionDetailsDto1 prdto = productionMapper.toDto1(production);
        List<PurchaseDto> peDtos = purchases.stream().map(purchaseMapper::toDto).toList();

        return new ProductionFullDataDto(prdto, peDtos, null, conversionsByBatch);
    }



    private String productionNumberGenerator() {
        Random random = new Random();
        String datePart = LocalDateTime.now().toString().replace("-", ""); // e.g., 20250303
        String randomPart = String.format("%04d", random.nextInt(10000)); // Ensures a 4-digit number
        return "PROD-" + datePart + "-" + randomPart;
    }


    private void createANonTransferredPurchaseTransfer(Purchase purchase, Production production) {
        var usage = purchase.getPurchaseUsage();
        if (usage != null && usage.getUsableWeightLeft() > 0) {
            System.out.println("Printing and debuging " + usage.getUsableWeightLeft());
            var pt = new PurchaseTransfer();
            pt.setPurchase(purchase);
            pt.setFromProduction(production);
            production.getOutgoingTransfers().add(pt);
        }
    }

    @Override
    public void finalizeProduction(Long productionId) {
        var production = productionRepository.findProductionById(productionId)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));
        var purchases = production.getPurchaseEntries();
        for (var purchase : purchases) {
            createANonTransferredPurchaseTransfer(purchase, production);
        }
        production.setFinalized(true);
        productionRepository.save(production);
    }

    @Override
    public List<ProductionDto> getNonFinalizedProductions() {
        List<Production> nonFinalizedProductions = productionRepository.findProductionsByFinalizedIsFalse();
        return nonFinalizedProductions.stream().map(productionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EvaluationDto> getProductionEvaluations(Long productionId) {
        return evaluationRepository.findEvaluationByProduction_Id(productionId)
                .stream().map(evaluationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deepDeleteProduction(Long id) {
        Production production = productionRepository.shallowLoadById(id)
                .orElseThrow(() -> new EntityException("Production not found", HttpStatus.NOT_FOUND));

        // Clear evaluations
        for (Evaluation eval : production.getEvaluations()) {
            eval.setProduction(null);
        }
        production.getEvaluations().clear();

        // Clear purchases and conversions
        for (Purchase purchase : production.getPurchaseEntries()) {
            for (Conversion conversion : purchase.getConversions()) {
                conversion.setPurchase(null);
            }
            purchase.getConversions().clear();
            purchase.setProduction(null);
        }
        production.getPurchaseEntries().clear();

        // Clear outgoing transfers
        for (PurchaseTransfer transfer : production.getOutgoingTransfers()) {
            transfer.setFromProduction(null);
        }
        production.getOutgoingTransfers().clear();

        // Delete ProductionStore
        if (production.getProductionStore() != null) {
            productionStoreRepository.delete(production.getProductionStore());
            production.setProductionStore(null);
        }

        // Delete ProductionBatches
        List<ProductionBatch> batches = productionBatchRepository
                .findProductionBatchByProduction_Id(production.getId())
                .orElse(new ArrayList<>());
        for (ProductionBatch batch : batches) {
            batch.setProduction(null);
        }
        productionBatchRepository.deleteAll(batches);

        // 💥 Delete ProductMixes
        List<ProductMix> mixes = productMixRepository
                .findProductMixByProduction_Id(production.getId());
        for (ProductMix mix : mixes) {
            mix.setProduction(null);
        }
        productMixRepository.deleteAll(mixes);
      /*  productMixRepository.flush(); // ⬅️ force DB to process deletion before deleting production
*/

        // 🧹 Final clean-up
        productionRepository.delete(production);
    }



}
