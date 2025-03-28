package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.IngredientMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.IngredientService;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.RawMaterialService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RawMaterialRepository rawMaterialRepository,
                                 IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    @Transactional
    public IngredientDto createIngredient(IngredientDto ingredient) {
       return ingredientMapper.toDto(ingredientRepository
               .save(ingredientMapper.toEntity(ingredient)));
    }


    @Override
    public IngredientDto getIngredientById(Long id) {
        return ingredientMapper.toDto(ingredientRepository.findById(id).orElse(null));
    }

    @Override
    public IngredientDto getIngredientByName(String name) {
        Optional<Ingredient> ingredient = ingredientRepository.findIngredientByName(name);
        return ingredientMapper.toDto(ingredient.orElse(null));
    }

    @Override
    public List<IngredientDto> createIngredients(List<IngredientDto> ingredients) {
        return ingredientRepository.saveAll(ingredients.stream()
                        .map(ingredientMapper::toEntity).collect(Collectors.toList()))
                .stream().map(ingredientMapper::toDto).toList();
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        return ingredientRepository.findAll().stream().map(ingredientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addRawMaterialToIngredient(Long ingredientId, RawMaterial rawMaterial) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        ingredient.getRawMaterials().add(rawMaterial);
        ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void removeRawMaterialFromIngredient(Long ingredientId, Long rawMaterialId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId).orElseThrow(() -> new IllegalArgumentException("RawMaterial not found"));
        ingredient.getRawMaterials().remove(rawMaterial);
        ingredientRepository.save(ingredient);
    }
}