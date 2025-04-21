package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.MaterialToIngredientMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.ProductionMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.ProductionDtoNew;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.MaterialToIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.PurchaseEntryDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.PurchaseEntryMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.MaterialToIngredient;
import com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry;
import com.justme8code.utterfresh_production_gathering_sys.repository.PurchaseEntryRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/// I decided to create separate service classes for purchase entry and materials to ingredient entry
/// This is because the two services are somehow not related in any way.
/// And also to follow the SRP principle of SOLID
@Service
@Transactional

public class PurchaseEntryService {

    private final ProductionRepository productionRepository;
    private final ProductionMapper productionMapper;
    private final MaterialToIngredientMapper materialToIngredientMapper;
    private final PurchaseEntryMapper purchaseEntryMapper;
    private final PurchaseEntryRepository purchaseEntryRepository;

    @Autowired
    public PurchaseEntryService(ProductionRepository productionRepository, ProductionMapper productionMapper,
                                MaterialToIngredientMapper materialToIngredientMapper,
                                PurchaseEntryMapper purchaseEntryMapper, PurchaseEntryRepository purchaseEntryRepository) {
        this.productionRepository = productionRepository;
        this.productionMapper = productionMapper;
        this.materialToIngredientMapper = materialToIngredientMapper;
        this.purchaseEntryMapper = purchaseEntryMapper;
        this.purchaseEntryRepository = purchaseEntryRepository;
    }

    public ProductionDtoNew getProductionEntries(long id) {
        Production production = productionRepository.findById(id).orElse(null);
        if (production == null) {
            throw new IllegalArgumentException("Production not found for ID: " + id);
        }
        return productionMapper.toDto4(production);
    }

    public void updateMaterialToIngredients(long productionId, List<MaterialToIngredientDto> materialToIngredientDtos) {
        Production production = productionRepository.findById(productionId)
            .orElseThrow(() -> new IllegalArgumentException("Production not found for ID: " + productionId));

        // Collect all purchase entry IDs from dtos where purchaseEntry is provided
        Set<Long> purchaseEntryIds = materialToIngredientDtos.stream()
            .filter(dto -> dto.getPurchaseEntry() != null)
            .map(dto -> dto.getPurchaseEntry().getId())
            .collect(Collectors.toSet());

        // Batch fetch purchase entries and map by id
        Map<Long, PurchaseEntry> purchaseEntryMap = purchaseEntryRepository.findAllById(purchaseEntryIds)
            .stream()
            .collect(Collectors.toMap(PurchaseEntry::getId, pe -> pe));

        // Process update and new creation separately
        for (MaterialToIngredientDto dto : materialToIngredientDtos) {
            if (dto.getId() != null) {
                MaterialToIngredient existing = production.getMaterialToIngredients().stream()
                        .filter(mti -> mti.getId().equals(dto.getId()))
                        .findFirst()
                        .orElse(null);
                if (existing != null) {
                    updateExistingMaterialToIngredient(production, existing, dto, purchaseEntryMap);
                } else {
                    System.out.println("Warning: MaterialToIngredient with ID " + dto.getId() + " not found in production " + productionId + ". Creating a new one.");
                    addNewMaterialToIngredient(production, dto, purchaseEntryMap);
                }
            } else {
                addNewMaterialToIngredient(production, dto, purchaseEntryMap);
            }
            System.out.println("Processed MaterialToIngredient for DTO: " + dto);
        }
        saveMaterialToIngredients(production);
    }

    private void updateExistingMaterialToIngredient(Production production, MaterialToIngredient materialToIngredient,
                                                      MaterialToIngredientDto dto, Map<Long, PurchaseEntry> purchaseEntryMap) {
        materialToIngredientMapper.partialUpdate(dto, materialToIngredient);
        materialToIngredient.setProduction(production);
        if (dto.getPurchaseEntry() != null) {
            PurchaseEntry purchaseEntry = purchaseEntryMap.get(dto.getPurchaseEntry().getId());
            if (purchaseEntry == null) {
                throw new IllegalArgumentException("PurchaseEntry not found for ID: " + dto.getPurchaseEntry().getId());
            }
            materialToIngredient.setPurchaseEntry(purchaseEntry);
            materialToIngredient.setTotalUsable(purchaseEntry.getUsable());
        }
    }

    private void addNewMaterialToIngredient(Production production, MaterialToIngredientDto dto,
                                              Map<Long, PurchaseEntry> purchaseEntryMap) {
        MaterialToIngredient materialToIngredient = materialToIngredientMapper.toEntity(dto);
        materialToIngredient.setProduction(production);
        if (dto.getPurchaseEntry() != null) {
            PurchaseEntry purchaseEntry = purchaseEntryMap.get(dto.getPurchaseEntry().getId());
            if (purchaseEntry == null) {
                throw new IllegalArgumentException("PurchaseEntry not found for ID: " + dto.getPurchaseEntry().getId());
            }
            materialToIngredient.setPurchaseEntry(purchaseEntry);
            materialToIngredient.setTotalUsable(purchaseEntry.getUsable());
        }
        production.getMaterialToIngredients().add(materialToIngredient);
    }

    private void saveMaterialToIngredients(Production production) {
        productionRepository.save(production);
    }

    public void updatePurchaseEntries(long productionId, List<PurchaseEntryDto> purchaseEntryDtos) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new IllegalArgumentException("Production not found for ID: " + productionId));

        for (PurchaseEntryDto dto : purchaseEntryDtos) {
            if (dto.getId() != null) {
                // Try to fetch an existing purchase entry
                PurchaseEntry existing = production.getPurchaseEntries().stream()
                        .filter(pe -> pe.getId().equals(dto.getId()))
                        .findFirst()
                        .orElse(null);
                if (existing != null) {
                    // Update existing purchase entry and its associated MaterialToIngredient
                    purchaseEntryMapper.partialUpdate(dto, existing);
                    existing.setUsable(dto.getUsable());
                    if (existing.getMaterialToIngredient() != null) {
                        existing.getMaterialToIngredient().setTotalUsable(dto.getUsable());
                    }
                } else {
                    // If not found, map DTO to a new entity and configure relations
                    PurchaseEntry newEntry = purchaseEntryMapper.toEntity(dto);
                    newEntry.setProduction(production);
                    if (newEntry.getMaterialToIngredient() != null) {
                        newEntry.getMaterialToIngredient().setTotalUsable(newEntry.getUsable());
                        newEntry.getMaterialToIngredient().setPurchaseEntry(newEntry);
                    }
                    production.getPurchaseEntries().add(newEntry);
                }
            } else {
                // New purchase entry insertion
                PurchaseEntry newEntry = purchaseEntryMapper.toEntity(dto);
                newEntry.setProduction(production);
                if (newEntry.getMaterialToIngredient() != null) {
                    newEntry.getMaterialToIngredient().setTotalUsable(newEntry.getUsable());
                    newEntry.getMaterialToIngredient().setPurchaseEntry(newEntry);
                }
                production.getPurchaseEntries().add(newEntry);
            }
        }
        productionRepository.save(production);
    }

    public void deletePurchaseEntries(long productionId, List<Long> purchaseEntryIds) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new IllegalArgumentException("Production not found for ID: " + productionId));
        production.getPurchaseEntries().removeIf(pe -> purchaseEntryIds.contains(pe.getId()));
        productionRepository.save(production);
    }

    public void deleteMaterialToIngredients(long productionId, List<Long> materialToIngredientIds) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new IllegalArgumentException("Production not found for ID: " + productionId));
        production.getMaterialToIngredients().removeIf(mti -> materialToIngredientIds.contains(mti.getId()));
        productionRepository.save(production);
    }

}
