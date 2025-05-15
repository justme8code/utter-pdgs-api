package com.justme8code.utterfresh_production_gathering_sys.services.helpers;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.RawMaterialRepository;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseHelper {
    public static void checkIfIngredientStoreExistOrCreateIngredientStore(Purchase purchase, Production production, RawMaterial rm) {
        // gets the ingredients of the raw material
        List<Ingredient> ing = rm.getIngredients();

        // If it's empty it throws an exception saying raw material has no ingredients
        if (ing.isEmpty()) {
            throw new EntityException("Raw material has no Ingredients", HttpStatus.BAD_REQUEST);
        }

        // get production store
        ProductionStore store = production.getProductionStore();

        // Create a Set of ingredient IDs already present in the store
        Set<Long> existingIngredientIds = store.getIngredientStores().stream()
                .map(ingredientStore -> ingredientStore.getIngredient().getId())
                .collect(Collectors.toSet());

        // new ingredient store to be added
        List<IngredientStore> newIngredientStores = new ArrayList<>();

        for (Ingredient ingredient : ing) {
            if (!existingIngredientIds.contains(ingredient.getId())) {
                IngredientStore newStore = new IngredientStore();
                newStore.setIngredient(ingredient);
                newStore.setUsableLitresLeft(0.0);
                newStore.setProductionStore(store);
                newIngredientStores.add(newStore);
            }
        }

        // Add all new IngredientStores in one go
        store.getIngredientStores().addAll(newIngredientStores);
    }
}
