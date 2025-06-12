package com.justme8code.utterfresh_production_gathering_sys.repository;

import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {
    Optional<Ingredient> findIngredientByName(String name);

    List<Ingredient> findByRawMaterials_NameIn(List<String> rawMaterialNames);

    List<Ingredient> findIngredientsByRawMaterialsIdIn(List<Long> rawMaterialIds);


}