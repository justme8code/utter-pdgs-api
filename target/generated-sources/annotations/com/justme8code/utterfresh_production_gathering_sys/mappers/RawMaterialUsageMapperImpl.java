package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.RUDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T15:20:15+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RawMaterialUsageMapperImpl implements RawMaterialUsageMapper {

    @Override
    public PurchaseUsage toEntity(RUDashboardData RUDashboardData) {
        if ( RUDashboardData == null ) {
            return null;
        }

        PurchaseUsage purchaseUsage = new PurchaseUsage();

        purchaseUsage.setPurchase( rUDashboardDataToPurchase( RUDashboardData ) );
        purchaseUsage.setId( RUDashboardData.getId() );
        purchaseUsage.setUsableWeightLeft( RUDashboardData.getUsableWeightLeft() );

        return purchaseUsage;
    }

    @Override
    public RUDashboardData toDto(PurchaseUsage purchaseUsage) {
        if ( purchaseUsage == null ) {
            return null;
        }

        String rawMaterialName = null;
        Long id = null;
        double usableWeightLeft = 0.0d;

        rawMaterialName = purchaseUsagePurchaseRawMaterialName( purchaseUsage );
        id = purchaseUsage.getId();
        usableWeightLeft = purchaseUsage.getUsableWeightLeft();

        RUDashboardData rUDashboardData = new RUDashboardData( id, usableWeightLeft, rawMaterialName );

        return rUDashboardData;
    }

    @Override
    public PurchaseUsage partialUpdate(RUDashboardData RUDashboardData, PurchaseUsage purchaseUsage) {
        if ( RUDashboardData == null ) {
            return purchaseUsage;
        }

        if ( purchaseUsage.getPurchase() == null ) {
            purchaseUsage.setPurchase( new Purchase() );
        }
        rUDashboardDataToPurchase1( RUDashboardData, purchaseUsage.getPurchase() );
        if ( RUDashboardData.getId() != null ) {
            purchaseUsage.setId( RUDashboardData.getId() );
        }
        purchaseUsage.setUsableWeightLeft( RUDashboardData.getUsableWeightLeft() );

        return purchaseUsage;
    }

    protected RawMaterial rUDashboardDataToRawMaterial(RUDashboardData rUDashboardData) {
        if ( rUDashboardData == null ) {
            return null;
        }

        RawMaterial rawMaterial = new RawMaterial();

        rawMaterial.setName( rUDashboardData.getRawMaterialName() );

        return rawMaterial;
    }

    protected Purchase rUDashboardDataToPurchase(RUDashboardData rUDashboardData) {
        if ( rUDashboardData == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setRawMaterial( rUDashboardDataToRawMaterial( rUDashboardData ) );

        return purchase;
    }

    private String purchaseUsagePurchaseRawMaterialName(PurchaseUsage purchaseUsage) {
        Purchase purchase = purchaseUsage.getPurchase();
        if ( purchase == null ) {
            return null;
        }
        RawMaterial rawMaterial = purchase.getRawMaterial();
        if ( rawMaterial == null ) {
            return null;
        }
        return rawMaterial.getName();
    }

    protected void rUDashboardDataToRawMaterial1(RUDashboardData rUDashboardData, RawMaterial mappingTarget) {
        if ( rUDashboardData == null ) {
            return;
        }

        if ( rUDashboardData.getRawMaterialName() != null ) {
            mappingTarget.setName( rUDashboardData.getRawMaterialName() );
        }
    }

    protected void rUDashboardDataToPurchase1(RUDashboardData rUDashboardData, Purchase mappingTarget) {
        if ( rUDashboardData == null ) {
            return;
        }

        if ( mappingTarget.getRawMaterial() == null ) {
            mappingTarget.setRawMaterial( new RawMaterial() );
        }
        rUDashboardDataToRawMaterial1( rUDashboardData, mappingTarget.getRawMaterial() );
    }
}
