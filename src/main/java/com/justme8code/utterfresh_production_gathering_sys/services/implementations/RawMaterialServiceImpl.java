package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.IngredientMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.RawMaterialMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
    private final RawMaterialRepository rawMaterialRepository;
    private final IngredientRepository ingredientRepository;
    private final RawMaterialMapper rawMaterialMapper;
    private final IngredientMapper ingredientMapper;

    public RawMaterialServiceImpl(RawMaterialRepository rawMaterialRepository, IngredientRepository ingredientRepository,
                                  RawMaterialMapper rawMaterialMapper,
                                  IngredientMapper ingredientMapper) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.ingredientRepository = ingredientRepository;
        this.rawMaterialMapper = rawMaterialMapper;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    @Transactional
    public RawMaterialDto createRawMaterial(RawMaterialDto rawMaterial) {
        return rawMaterialMapper.toDto(
                rawMaterialRepository.save(rawMaterialMapper.toEntity(rawMaterial)));
    }

    @Override
    public RawMaterialDto getRawMaterialById(Long id) {
        return rawMaterialMapper.toDto(rawMaterialRepository.findById(id).orElse(null));
    }

    @Override
    public List<RawMaterialDto> createRawMaterials(List<RawMaterialDto> rawMaterials) {
        System.out.println(rawMaterials);
        List<RawMaterial> rawMaterialList = rawMaterials.stream().map(rawMaterialMapper::toEntity).toList();
        rawMaterialRepository.saveAll(rawMaterialList);
        return rawMaterialRepository.findAll().stream().map(rawMaterialMapper::toDto).toList();
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
    public void addIngredientToRawMaterial(Long rawMaterialId, List<IngredientDto> ingredients) {
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId)
                .orElseThrow(() -> new IllegalArgumentException("RawMaterial not found"));

        // Convert DTOs to entity objects
        List<Ingredient> newIngredients = ingredients.stream()
                .map(ingredientMapper::toEntity)
                .toList();

        // Clear existing ingredients and replace them with the new ones
        rawMaterial.getIngredients().clear();
        rawMaterial.getIngredients().addAll(newIngredients);

        // Save the updated entity
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