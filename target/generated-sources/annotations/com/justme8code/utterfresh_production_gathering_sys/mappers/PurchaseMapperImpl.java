package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T19:52:51+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public Purchase toEntity(PurchaseDto purchaseDto) {
        if ( purchaseDto == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setId( purchaseDto.getId() );
        purchase.setRawMaterial( rawMaterialDto1ToRawMaterial( purchaseDto.getRawMaterial() ) );
        purchase.setSupplier( supplierDto1ToSupplier( purchaseDto.getSupplier() ) );
        purchase.setUomQty( purchaseDto.getUomQty() );
        purchase.setWeight( purchaseDto.getWeight() );
        purchase.setProductionLostWeight( purchaseDto.getProductionLostWeight() );
        purchase.setUsableWeight( purchaseDto.getUsableWeight() );
        purchase.setCost( purchaseDto.getCost() );
        purchase.setAvgCost( purchaseDto.getAvgCost() );
        purchase.setAvgWeightPerUOM( purchaseDto.getAvgWeightPerUOM() );
        purchase.setTransferred( purchaseDto.isTransferred() );
        purchase.setPurchaseUsage( purchaseUsageDto1ToPurchaseUsage( purchaseDto.getPurchaseUsage() ) );

        return purchase;
    }

    @Override
    public PurchaseDto toDto(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        Long id = null;
        double productionLostWeight = 0.0d;
        double uomQty = 0.0d;
        double weight = 0.0d;
        double usableWeight = 0.0d;
        double cost = 0.0d;
        double avgCost = 0.0d;
        double avgWeightPerUOM = 0.0d;
        boolean transferred = false;
        PurchaseDto.RawMaterialDto1 rawMaterial = null;
        PurchaseDto.SupplierDto1 supplier = null;
        PurchaseDto.PurchaseUsageDto1 purchaseUsage = null;

        id = purchase.getId();
        productionLostWeight = purchase.getProductionLostWeight();
        uomQty = purchase.getUomQty();
        weight = purchase.getWeight();
        usableWeight = purchase.getUsableWeight();
        cost = purchase.getCost();
        avgCost = purchase.getAvgCost();
        avgWeightPerUOM = purchase.getAvgWeightPerUOM();
        transferred = purchase.isTransferred();
        rawMaterial = rawMaterialToRawMaterialDto1( purchase.getRawMaterial() );
        supplier = supplierToSupplierDto1( purchase.getSupplier() );
        purchaseUsage = purchaseUsageToPurchaseUsageDto1( purchase.getPurchaseUsage() );

        PurchaseDto purchaseDto = new PurchaseDto( id, productionLostWeight, uomQty, weight, usableWeight, cost, avgCost, avgWeightPerUOM, transferred, rawMaterial, supplier, purchaseUsage );

        return purchaseDto;
    }

    @Override
    public Purchase partialUpdate(PurchaseDto purchaseDto, Purchase purchase) {
        if ( purchaseDto == null ) {
            return purchase;
        }

        if ( purchaseDto.getId() != null ) {
            purchase.setId( purchaseDto.getId() );
        }
        if ( purchaseDto.getRawMaterial() != null ) {
            if ( purchase.getRawMaterial() == null ) {
                purchase.setRawMaterial( new RawMaterial() );
            }
            rawMaterialDto1ToRawMaterial1( purchaseDto.getRawMaterial(), purchase.getRawMaterial() );
        }
        if ( purchaseDto.getSupplier() != null ) {
            if ( purchase.getSupplier() == null ) {
                purchase.setSupplier( new Supplier() );
            }
            supplierDto1ToSupplier1( purchaseDto.getSupplier(), purchase.getSupplier() );
        }
        purchase.setUomQty( purchaseDto.getUomQty() );
        purchase.setWeight( purchaseDto.getWeight() );
        purchase.setProductionLostWeight( purchaseDto.getProductionLostWeight() );
        purchase.setUsableWeight( purchaseDto.getUsableWeight() );
        purchase.setCost( purchaseDto.getCost() );
        purchase.setAvgCost( purchaseDto.getAvgCost() );
        purchase.setAvgWeightPerUOM( purchaseDto.getAvgWeightPerUOM() );
        purchase.setTransferred( purchaseDto.isTransferred() );
        if ( purchaseDto.getPurchaseUsage() != null ) {
            if ( purchase.getPurchaseUsage() == null ) {
                purchase.setPurchaseUsage( new PurchaseUsage() );
            }
            purchaseUsageDto1ToPurchaseUsage1( purchaseDto.getPurchaseUsage(), purchase.getPurchaseUsage() );
        }

        return purchase;
    }

    protected RawMaterial rawMaterialDto1ToRawMaterial(PurchaseDto.RawMaterialDto1 rawMaterialDto1) {
        if ( rawMaterialDto1 == null ) {
            return null;
        }

        RawMaterial rawMaterial = new RawMaterial();

        rawMaterial.setId( rawMaterialDto1.getId() );
        rawMaterial.setName( rawMaterialDto1.getName() );
        rawMaterial.setUom( rawMaterialDto1.getUom() );

        return rawMaterial;
    }

    protected Supplier supplierDto1ToSupplier(PurchaseDto.SupplierDto1 supplierDto1) {
        if ( supplierDto1 == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( supplierDto1.getId() );
        supplier.setFullName( supplierDto1.getFullName() );

        return supplier;
    }

    protected PurchaseUsage purchaseUsageDto1ToPurchaseUsage(PurchaseDto.PurchaseUsageDto1 purchaseUsageDto1) {
        if ( purchaseUsageDto1 == null ) {
            return null;
        }

        PurchaseUsage purchaseUsage = new PurchaseUsage();

        purchaseUsage.setId( purchaseUsageDto1.getId() );
        purchaseUsage.setUsableWeightLeft( purchaseUsageDto1.getUsableWeightLeft() );
        purchaseUsage.setTotalKgUsed( purchaseUsageDto1.getTotalKgUsed() );

        return purchaseUsage;
    }

    protected PurchaseDto.RawMaterialDto1 rawMaterialToRawMaterialDto1(RawMaterial rawMaterial) {
        if ( rawMaterial == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String uom = null;

        id = rawMaterial.getId();
        name = rawMaterial.getName();
        uom = rawMaterial.getUom();

        PurchaseDto.RawMaterialDto1 rawMaterialDto1 = new PurchaseDto.RawMaterialDto1( id, name, uom );

        return rawMaterialDto1;
    }

    protected PurchaseDto.SupplierDto1 supplierToSupplierDto1(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;

        id = supplier.getId();
        fullName = supplier.getFullName();

        PurchaseDto.SupplierDto1 supplierDto1 = new PurchaseDto.SupplierDto1( id, fullName );

        return supplierDto1;
    }

    protected PurchaseDto.PurchaseUsageDto1 purchaseUsageToPurchaseUsageDto1(PurchaseUsage purchaseUsage) {
        if ( purchaseUsage == null ) {
            return null;
        }

        Long id = null;
        double usableWeightLeft = 0.0d;
        double totalKgUsed = 0.0d;

        id = purchaseUsage.getId();
        usableWeightLeft = purchaseUsage.getUsableWeightLeft();
        totalKgUsed = purchaseUsage.getTotalKgUsed();

        PurchaseDto.PurchaseUsageDto1 purchaseUsageDto1 = new PurchaseDto.PurchaseUsageDto1( id, usableWeightLeft, totalKgUsed );

        return purchaseUsageDto1;
    }

    protected void rawMaterialDto1ToRawMaterial1(PurchaseDto.RawMaterialDto1 rawMaterialDto1, RawMaterial mappingTarget) {
        if ( rawMaterialDto1 == null ) {
            return;
        }

        if ( rawMaterialDto1.getId() != null ) {
            mappingTarget.setId( rawMaterialDto1.getId() );
        }
        if ( rawMaterialDto1.getName() != null ) {
            mappingTarget.setName( rawMaterialDto1.getName() );
        }
        if ( rawMaterialDto1.getUom() != null ) {
            mappingTarget.setUom( rawMaterialDto1.getUom() );
        }
    }

    protected void supplierDto1ToSupplier1(PurchaseDto.SupplierDto1 supplierDto1, Supplier mappingTarget) {
        if ( supplierDto1 == null ) {
            return;
        }

        if ( supplierDto1.getId() != null ) {
            mappingTarget.setId( supplierDto1.getId() );
        }
        if ( supplierDto1.getFullName() != null ) {
            mappingTarget.setFullName( supplierDto1.getFullName() );
        }
    }

    protected void purchaseUsageDto1ToPurchaseUsage1(PurchaseDto.PurchaseUsageDto1 purchaseUsageDto1, PurchaseUsage mappingTarget) {
        if ( purchaseUsageDto1 == null ) {
            return;
        }

        if ( purchaseUsageDto1.getId() != null ) {
            mappingTarget.setId( purchaseUsageDto1.getId() );
        }
        mappingTarget.setUsableWeightLeft( purchaseUsageDto1.getUsableWeightLeft() );
        mappingTarget.setTotalKgUsed( purchaseUsageDto1.getTotalKgUsed() );
    }
}
