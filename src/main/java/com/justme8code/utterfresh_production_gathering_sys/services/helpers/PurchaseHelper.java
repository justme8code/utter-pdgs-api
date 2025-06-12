package com.justme8code.utterfresh_production_gathering_sys.services.helpers;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.PurchaseMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterialStore;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public static void checkIfPurchaseRawMaterialExistsOrCreateRawMaterialStore(Purchase purchase, ProductionStore store) {
        Set<Long> existingRawMaterialIds = store.getRawMaterialStores().stream()
                .map(rawMaterialStore -> rawMaterialStore.getRawMaterial().getId())
                .collect(Collectors.toSet());

        List<RawMaterialStore> newRawMaterialStores = new ArrayList<>();

        if (!existingRawMaterialIds.contains(purchase.getRawMaterial().getId())) {
            RawMaterialStore newStore = new RawMaterialStore();
            newStore.setRawMaterial(purchase.getRawMaterial());
            newStore.setTotalUsableQuantity(purchase.getPurchaseUsage().getUsableWeightLeft());
            newStore.setTotalUsedQuantity(purchase.getPurchaseUsage().getTotalKgUsed());
            newStore.setProductionStore(store);
            newRawMaterialStores.add(newStore);
            store.getRawMaterialStores().addAll(newRawMaterialStores);
            System.out.println("Always running here.");
        } else {
            System.out.println("Contains");
            addToRawMaterialStore(purchase, store);
        }
    }

    public static void addToRawMaterialStore(Purchase purchase, ProductionStore store) {
        List<RawMaterialStore> stores = store.getRawMaterialStores();
        stores.stream()
                .filter(rawMaterialStore -> Objects.equals(rawMaterialStore.getRawMaterial().getId(), purchase.getRawMaterial().getId()))
                .findFirst().ifPresent(found -> found.setTotalUsableQuantity(
                        found.getTotalUsableQuantity() + purchase.getPurchaseUsage().getUsableWeightLeft()
                ));
    }

    public static void createPurchaseUsage(Purchase purchase, Production production, RawMaterial rm, PurchaseMapper purchaseMapper) {
        // Creates a new purchase usage to track the purchase
        PurchaseUsage purchaseUsage = new PurchaseUsage();
        purchaseUsage.setUsableWeightLeft(purchase.getUsableWeight());// sets initial data
        // converts to entity purchase from dto
        purchase.setPurchaseUsage(purchaseUsage);
        // set which purchase the purchase usage is associated to
        purchaseUsage.setPurchase(purchase);
        // sets purchase production
        purchase.setProduction(production);

        PurchaseHelper.checkIfIngredientStoreExistOrCreateIngredientStore(purchase, production, rm);
        PurchaseHelper.checkIfPurchaseRawMaterialExistsOrCreateRawMaterialStore(purchase, production.getProductionStore());
    }
}
