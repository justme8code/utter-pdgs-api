package com.justme8code.utterfresh_production_gathering_sys.services.helpers;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.models.*;
import com.justme8code.utterfresh_production_gathering_sys.repository.ProductionRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ProductionHelper {

    public static Production findProductionByIdHelper(ProductionRepository productionRepository, long productionId) {
        return productionRepository.findById(productionId)
                .orElseThrow(() -> new IllegalArgumentException("Production not found for ID: " + productionId));
    }

    public static void createOrAddToRawMaterialStore(Purchase purchase, Production production){
        RawMaterial used = purchase.getRawMaterial();
        double usableQuantity = purchase.getPurchaseUsage().getUsableWeightLeft();
        double totalUsed = purchase.getPurchaseUsage().getTotalKgUsed();
        List<RawMaterialStore> stores = production.getProductionStore().getRawMaterialStores();
        RawMaterialStore found = stores.stream().filter(rms -> rms.getId().equals(used.getId())).findFirst().orElse(null);
        if(stores.isEmpty() || found == null){
            RawMaterialStore rawMaterialStore = new RawMaterialStore();
            rawMaterialStore.setRawMaterial(used);
            rawMaterialStore.setTotalUsableQuantity(usableQuantity);
            rawMaterialStore.setTotalUsedQuantity(totalUsed);
            rawMaterialStore.setProductionStore(production.getProductionStore());
            production.getProductionStore().getRawMaterialStores().add(rawMaterialStore);
        }else{
            found.setTotalUsableQuantity(found.getTotalUsableQuantity() + usableQuantity);
            found.setTotalUsedQuantity(found.getTotalUsedQuantity() + totalUsed);
        }
    }


    public static void tempRemoveFromRawMaterialStore(Purchase purchase,Production production){
        RawMaterial used = purchase.getRawMaterial();
        PurchaseUsage purchaseUsage = purchase.getPurchaseUsage();
        List<RawMaterialStore> stores = production.getProductionStore().getRawMaterialStores();
        RawMaterialStore found = stores.stream().filter(rms -> rms.getId().equals(used.getId())).findFirst().orElse(null);

        if(found != null){
            found.setTotalUsableQuantity(found.getTotalUsableQuantity()-purchaseUsage.getUsableWeightLeft());
            found.setTotalUsedQuantity(found.getTotalUsedQuantity()-purchaseUsage.getTotalKgUsed());
        }else{
            throw new EntityException("Can't remove from store", HttpStatus.NOT_FOUND);
        }
    }


}
