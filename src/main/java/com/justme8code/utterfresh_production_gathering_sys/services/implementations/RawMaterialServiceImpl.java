package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.RawMaterialMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
    private final RawMaterialRepository rawMaterialRepository;
    private final IngredientRepository ingredientRepository;
    private final RawMaterialMapper rawMaterialMapper;

    public RawMaterialServiceImpl(RawMaterialRepository rawMaterialRepository, IngredientRepository ingredientRepository,
                                  RawMaterialMapper rawMaterialMapper) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.ingredientRepository = ingredientRepository;
        this.rawMaterialMapper = rawMaterialMapper;
    }

    @Override
    @Transactional
    public void storeRawMaterial(RawMaterial rawMaterial) {
        rawMaterialRepository.save(rawMaterial);
    }

    @Override
    public RawMaterial getRawMaterialById(Long id) {
        return rawMaterialRepository.findById(id).orElse(null);
    }

    @Override
    public void createRawMaterials(List<RawMaterial> rawMaterials) {
        rawMaterialRepository.saveAll(rawMaterials);
    }

    @Override
    public List<RawMaterialDto> getAllRawMaterials() {
        return rawMaterialRepository.findAll().stream().map(rawMaterialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRawMaterialById(Long id) {
        rawMaterialRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addIngredientToRawMaterial(Long rawMaterialId, Ingredient ingredient) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId).orElseThrow(() -> new IllegalArgumentException("RawMaterial not found"));
        rawMaterial.getIngredients().add(ingredient);
        rawMaterialRepository.save(rawMaterial);
    }

    @Override
    @Transactional
    public void removeIngredientFromRawMaterial(Long rawMaterialId, Long ingredientId) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId).orElseThrow(() -> new IllegalArgumentException("RawMaterial not found"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        rawMaterial.getIngredients().remove(ingredient);
        rawMaterialRepository.save(rawMaterial);
    }
}