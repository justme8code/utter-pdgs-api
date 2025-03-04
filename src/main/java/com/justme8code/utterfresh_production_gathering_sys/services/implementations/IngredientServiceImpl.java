package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.IngredientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RawMaterialRepository rawMaterialRepository) {
        this.ingredientRepository = ingredientRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Override
    @Transactional
    public void storeIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
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