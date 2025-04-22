package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Production}
 */
@Value
public class ProductionDtoNew implements Serializable {

    Long id;
    LocalDateTime createdAt;

    String productionNumber;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    StaffDto staff;
    Production.ProductionStatus status;
    List<ProductionBatchDto> productionBatches;
    List<PurchaseEntryDto> purchaseEntries;
    List<MaterialToIngredientDto> materialToIngredients;

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.ProductionBatch}
     */
    @Value
    public static class ProductionBatchDto implements Serializable {
        Long id;
        String name;
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.PurchaseEntry}
     */
    @Value
    public static class PurchaseEntryDto implements Serializable {
        Long id;
        RawMaterialDto1 rawMaterial;
        SupplierDto supplier;
        String uom;
        double qty;
        double weight;
        double productionLost;
        double usable;
        double cost;
        double avgCost;
        double avgWeightPerUOM;

        /**
         * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
         */
        @Value
        public static class RawMaterialDto1 implements Serializable {
            Long id;
            String name;
            String uom;
            List<MaterialToIngredientDto.IngredientDto2> ingredients;
        }
    }

    /**
     * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.MaterialToIngredient}
     */
    @Value
    public static class MaterialToIngredientDto implements Serializable {
        Long id;
        PurchaseEntryDto purchaseEntry;
        double totalUsable;
        double litresLost;
        double usable;
        int outPutLitres;
        int batch;
        double litresPerKg;
        double costPerLitre;
        double rawBrix;

        /**
         * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.RawMaterial}
         */
        @Value
        public static class RawMaterialDto1 implements Serializable {
            Long id;
            String name;
        }

        /**
         * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Ingredient}
         */
        @Value
        public static class IngredientDto2 implements Serializable {
            Long id;
            String name;
        }

        @Value
        public static class PurchaseEntryDto implements Serializable {
            Long id;
            ProductionDtoNew.PurchaseEntryDto.RawMaterialDto1 rawMaterial;
            double cost;

        }
    }
}