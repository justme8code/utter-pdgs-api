package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.IngredientMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.IngredientDto1;
import com.justme8code.utterfresh_production_gathering_sys.models.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.repository.IngredientRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.IngredientService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
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
    public IngredientDto1 createIngredient(IngredientDto1 ingredient) {
       return ingredientMapper.toDto1(ingredientMapper.toEntity(ingredientMapper
               .toDto1(ingredientRepository.save(ingredientMapper.toEntity(ingredient)))));
    }

    @Override
    public IngredientDto1 getIngredientById(Long id) {
        return null;
    }

    @Override
    public IngredientDto1 getIngredientByName(String name) {
       Ingredient ingredient = ingredientRepository.findIngredientByName(name)
               .orElseThrow(()-> new EntityException("Not Found", HttpStatus.NOT_FOUND));
       return ingredientMapper.toDto1(ingredient);
    }

    @Override
    public List<IngredientDto1> createIngredients(List<IngredientDto1> ingredients) {
        ingredientRepository.saveAll(ingredients.stream().map(ingredientMapper::toEntity).collect(Collectors.toList()));
        return ingredientRepository.findAll().stream().map(ingredientMapper::toDto1).collect(Collectors.toList());
    }

    @Override
    public List<IngredientDto1> getAllIngredients() {
        return ingredientRepository.findAll().stream().map(ingredientMapper::toDto1).collect(Collectors.toList());
    }

    @Override
    public IngredientDto1 updateIngredient(long ingredientId,IngredientDto1 ingredient) {
         Ingredient retreivedIngredient = ingredientRepository.findById(ingredientId).orElseThrow(()-> new EntityException("Not Found", HttpStatus.NOT_FOUND));
         Ingredient ingredient2 = ingredientMapper.toEntity(ingredient);
         retreivedIngredient.setName(ingredient2.getName());
         retreivedIngredient.setRawMaterials(ingredient2.getRawMaterials());
         return ingredientMapper.toDto1(ingredientRepository.save(retreivedIngredient));
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