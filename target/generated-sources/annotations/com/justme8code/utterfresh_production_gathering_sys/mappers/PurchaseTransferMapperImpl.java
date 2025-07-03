package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseTransferDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseTransfer;
import com.justme8code.utterfresh_production_gathering_sys.models.event.PurchaseUsage;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T19:52:50+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class PurchaseTransferMapperImpl implements PurchaseTransferMapper {

    @Override
    public PurchaseTransfer toEntity(PurchaseTransferDto purchaseTransferDto) {
        if ( purchaseTransferDto == null ) {
            return null;
        }

        PurchaseTransfer purchaseTransfer = new PurchaseTransfer();

        purchaseTransfer.setFromProduction( purchaseTransferDtoToProduction( purchaseTransferDto ) );
        purchaseTransfer.setPurchase( purchaseDto2ToPurchase( purchaseTransferDto.getPurchase() ) );
        purchaseTransfer.setId( purchaseTransferDto.getId() );
        purchaseTransfer.setTransferred( purchaseTransferDto.isTransferred() );
        purchaseTransfer.setTransferNotes( purchaseTransferDto.getTransferNotes() );

        return purchaseTransfer;
    }

    @Override
    public PurchaseTransferDto toDto(PurchaseTransfer purchaseTransfer) {
        if ( purchaseTransfer == null ) {
            return null;
        }

        PurchaseTransferDto.PurchaseDto2 purchase = null;
        String fromProductionName = null;
        Long fromProductionId = null;
        Long id = null;
        boolean transferred = false;
        String transferNotes = null;

        purchase = purchaseToPurchaseDto2( purchaseTransfer.getPurchase() );
        fromProductionName = purchaseTransferFromProductionName( purchaseTransfer );
        fromProductionId = purchaseTransferFromProductionId( purchaseTransfer );
        id = purchaseTransfer.getId();
        transferred = purchaseTransfer.isTransferred();
        transferNotes = purchaseTransfer.getTransferNotes();

        PurchaseTransferDto purchaseTransferDto = new PurchaseTransferDto( id, purchase, fromProductionId, fromProductionName, transferred, transferNotes );

        return purchaseTransferDto;
    }

    @Override
    public PurchaseTransfer partialUpdate(PurchaseTransferDto purchaseTransferDto, PurchaseTransfer purchaseTransfer) {
        if ( purchaseTransferDto == null ) {
            return purchaseTransfer;
        }

        if ( purchaseTransfer.getFromProduction() == null ) {
            purchaseTransfer.setFromProduction( new Production() );
        }
        purchaseTransferDtoToProduction1( purchaseTransferDto, purchaseTransfer.getFromProduction() );
        if ( purchaseTransferDto.getPurchase() != null ) {
            if ( purchaseTransfer.getPurchase() == null ) {
                purchaseTransfer.setPurchase( new Purchase() );
            }
            purchaseDto2ToPurchase1( purchaseTransferDto.getPurchase(), purchaseTransfer.getPurchase() );
        }
        if ( purchaseTransferDto.getId() != null ) {
            purchaseTransfer.setId( purchaseTransferDto.getId() );
        }
        purchaseTransfer.setTransferred( purchaseTransferDto.isTransferred() );
        if ( purchaseTransferDto.getTransferNotes() != null ) {
            purchaseTransfer.setTransferNotes( purchaseTransferDto.getTransferNotes() );
        }

        return purchaseTransfer;
    }

    protected Production purchaseTransferDtoToProduction(PurchaseTransferDto purchaseTransferDto) {
        if ( purchaseTransferDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setName( purchaseTransferDto.getFromProductionName() );
        production.setId( purchaseTransferDto.getFromProductionId() );

        return production;
    }

    protected RawMaterial purchaseDto2ToRawMaterial(PurchaseTransferDto.PurchaseDto2 purchaseDto2) {
        if ( purchaseDto2 == null ) {
            return null;
        }

        RawMaterial rawMaterial = new RawMaterial();

        rawMaterial.setName( purchaseDto2.getRawMaterialName() );
        rawMaterial.setUom( purchaseDto2.getRawMaterialUom() );

        return rawMaterial;
    }

    protected PurchaseUsage purchaseDto2ToPurchaseUsage(PurchaseTransferDto.PurchaseDto2 purchaseDto2) {
        if ( purchaseDto2 == null ) {
            return null;
        }

        PurchaseUsage purchaseUsage = new PurchaseUsage();

        purchaseUsage.setTotalKgUsed( purchaseDto2.getPurchaseUsageTotalKgUsed() );
        purchaseUsage.setUsableWeightLeft( purchaseDto2.getPurchaseUsageUsableWeightLeft() );

        return purchaseUsage;
    }

    protected Purchase purchaseDto2ToPurchase(PurchaseTransferDto.PurchaseDto2 purchaseDto2) {
        if ( purchaseDto2 == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setRawMaterial( purchaseDto2ToRawMaterial( purchaseDto2 ) );
        purchase.setPurchaseUsage( purchaseDto2ToPurchaseUsage( purchaseDto2 ) );
        purchase.setId( purchaseDto2.getId() );
        purchase.setCost( purchaseDto2.getCost() );
        purchase.setTransferred( purchaseDto2.isTransferred() );

        return purchase;
    }

    private String purchaseRawMaterialName(Purchase purchase) {
        RawMaterial rawMaterial = purchase.getRawMaterial();
        if ( rawMaterial == null ) {
            return null;
        }
        return rawMaterial.getName();
    }

    private double purchasePurchaseUsageTotalKgUsed(Purchase purchase) {
        PurchaseUsage purchaseUsage = purchase.getPurchaseUsage();
        if ( purchaseUsage == null ) {
            return 0.0d;
        }
        return purchaseUsage.getTotalKgUsed();
    }

    private double purchasePurchaseUsageUsableWeightLeft(Purchase purchase) {
        PurchaseUsage purchaseUsage = purchase.getPurchaseUsage();
        if ( purchaseUsage == null ) {
            return 0.0d;
        }
        return purchaseUsage.getUsableWeightLeft();
    }

    private String purchaseRawMaterialUom(Purchase purchase) {
        RawMaterial rawMaterial = purchase.getRawMaterial();
        if ( rawMaterial == null ) {
            return null;
        }
        return rawMaterial.getUom();
    }

    protected PurchaseTransferDto.PurchaseDto2 purchaseToPurchaseDto2(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        String rawMaterialName = null;
        double purchaseUsageTotalKgUsed = 0.0d;
        double purchaseUsageUsableWeightLeft = 0.0d;
        String rawMaterialUom = null;
        Long id = null;
        double cost = 0.0d;
        boolean transferred = false;

        rawMaterialName = purchaseRawMaterialName( purchase );
        purchaseUsageTotalKgUsed = purchasePurchaseUsageTotalKgUsed( purchase );
        purchaseUsageUsableWeightLeft = purchasePurchaseUsageUsableWeightLeft( purchase );
        rawMaterialUom = purchaseRawMaterialUom( purchase );
        id = purchase.getId();
        cost = purchase.getCost();
        transferred = purchase.isTransferred();

        PurchaseTransferDto.PurchaseDto2 purchaseDto2 = new PurchaseTransferDto.PurchaseDto2( id, rawMaterialName, rawMaterialUom, cost, purchaseUsageUsableWeightLeft, purchaseUsageTotalKgUsed, transferred );

        return purchaseDto2;
    }

    private String purchaseTransferFromProductionName(PurchaseTransfer purchaseTransfer) {
        Production fromProduction = purchaseTransfer.getFromProduction();
        if ( fromProduction == null ) {
            return null;
        }
        return fromProduction.getName();
    }

    private Long purchaseTransferFromProductionId(PurchaseTransfer purchaseTransfer) {
        Production fromProduction = purchaseTransfer.getFromProduction();
        if ( fromProduction == null ) {
            return null;
        }
        return fromProduction.getId();
    }

    protected void purchaseTransferDtoToProduction1(PurchaseTransferDto purchaseTransferDto, Production mappingTarget) {
        if ( purchaseTransferDto == null ) {
            return;
        }

        if ( purchaseTransferDto.getFromProductionName() != null ) {
            mappingTarget.setName( purchaseTransferDto.getFromProductionName() );
        }
        if ( purchaseTransferDto.getFromProductionId() != null ) {
            mappingTarget.setId( purchaseTransferDto.getFromProductionId() );
        }
    }

    protected void purchaseDto2ToRawMaterial1(PurchaseTransferDto.PurchaseDto2 purchaseDto2, RawMaterial mappingTarget) {
        if ( purchaseDto2 == null ) {
            return;
        }

        if ( purchaseDto2.getRawMaterialName() != null ) {
            mappingTarget.setName( purchaseDto2.getRawMaterialName() );
        }
        if ( purchaseDto2.getRawMaterialUom() != null ) {
            mappingTarget.setUom( purchaseDto2.getRawMaterialUom() );
        }
    }

    protected void purchaseDto2ToPurchaseUsage1(PurchaseTransferDto.PurchaseDto2 purchaseDto2, PurchaseUsage mappingTarget) {
        if ( purchaseDto2 == null ) {
            return;
        }

        mappingTarget.setTotalKgUsed( purchaseDto2.getPurchaseUsageTotalKgUsed() );
        mappingTarget.setUsableWeightLeft( purchaseDto2.getPurchaseUsageUsableWeightLeft() );
    }

    protected void purchaseDto2ToPurchase1(PurchaseTransferDto.PurchaseDto2 purchaseDto2, Purchase mappingTarget) {
        if ( purchaseDto2 == null ) {
            return;
        }

        if ( mappingTarget.getRawMaterial() == null ) {
            mappingTarget.setRawMaterial( new RawMaterial() );
        }
        purchaseDto2ToRawMaterial1( purchaseDto2, mappingTarget.getRawMaterial() );
        if ( mappingTarget.getPurchaseUsage() == null ) {
            mappingTarget.setPurchaseUsage( new PurchaseUsage() );
        }
        purchaseDto2ToPurchaseUsage1( purchaseDto2, mappingTarget.getPurchaseUsage() );
        if ( purchaseDto2.getId() != null ) {
            mappingTarget.setId( purchaseDto2.getId() );
        }
        mappingTarget.setCost( purchaseDto2.getCost() );
        mappingTarget.setTransferred( purchaseDto2.isTransferred() );
    }
}
