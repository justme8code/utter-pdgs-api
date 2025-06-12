package com.justme8code.utterfresh_production_gathering_sys.dtos.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterialStore;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ProductionStore}
 */
@Value
public class ProductionStoreDto implements Serializable {
    Long id;
    Long productionId;
    List<IngredientStoreDto> ingredientStores;
    List<RawMaterialStoreDto> rawMaterialStores;

    /**
     * DTO for {@link IngredientStore}
     */
    @Value
    public static class IngredientStoreDto implements Serializable {
        Long id;
        IngredientDto ingredient;
        double usableLitresLeft;
    }

    /**
     * DTO for {@link RawMaterialStore}
     */
    @Value
    public static class RawMaterialStoreDto implements Serializable {
        Long id;
        PurchaseDto.RawMaterialDto1 rawMaterial;
        double totalUsableQuantity;
        double totalUsedQuantity;
    }


}