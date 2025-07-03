package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionPayload;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ConversionField;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Purchase;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T11:41:33+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class ConversionMapperImpl implements ConversionMapper {

    @Override
    public Conversion toEntity(ConversionDto conversionDto) {
        if ( conversionDto == null ) {
            return null;
        }

        Conversion conversion = new Conversion();

        conversion.setPurchase( conversionDtoToPurchase( conversionDto ) );
        conversion.setProduction( conversionDtoToProduction( conversionDto ) );
        conversion.setCreatedAt( conversionDto.getCreatedAt() );
        conversion.setId( conversionDto.getId() );
        conversion.setBatch( conversionDto.getBatch() );
        conversion.setFields( conversionFieldDtoListToConversionFieldList( conversionDto.getFields() ) );

        linkFields( conversion );

        return conversion;
    }

    @Override
    public ConversionDto toDto(Conversion conversion) {
        if ( conversion == null ) {
            return null;
        }

        Long purchaseId = null;
        Long productionId = null;
        List<ConversionDto.ConversionFieldDto> fields = null;
        Long id = null;
        int batch = 0;
        LocalDateTime createdAt = null;

        purchaseId = conversionPurchaseId( conversion );
        productionId = conversionProductionId( conversion );
        fields = conversionFieldListToConversionFieldDtoList( conversion.getFields() );
        id = conversion.getId();
        batch = conversion.getBatch();
        createdAt = conversion.getCreatedAt();

        ConversionDto conversionDto = new ConversionDto( id, batch, productionId, purchaseId, fields, createdAt );

        return conversionDto;
    }

    @Override
    public Conversion partialUpdate(ConversionDto conversionDto, Conversion conversion) {
        if ( conversionDto == null ) {
            return conversion;
        }

        if ( conversion.getPurchase() == null ) {
            conversion.setPurchase( new Purchase() );
        }
        conversionDtoToPurchase1( conversionDto, conversion.getPurchase() );
        if ( conversion.getProduction() == null ) {
            conversion.setProduction( new Production() );
        }
        conversionDtoToProduction1( conversionDto, conversion.getProduction() );
        if ( conversionDto.getCreatedAt() != null ) {
            conversion.setCreatedAt( conversionDto.getCreatedAt() );
        }
        if ( conversionDto.getId() != null ) {
            conversion.setId( conversionDto.getId() );
        }
        conversion.setBatch( conversionDto.getBatch() );
        if ( conversion.getFields() != null ) {
            List<ConversionField> list = conversionFieldDtoListToConversionFieldList( conversionDto.getFields() );
            if ( list != null ) {
                conversion.getFields().clear();
                conversion.getFields().addAll( list );
            }
        }
        else {
            List<ConversionField> list = conversionFieldDtoListToConversionFieldList( conversionDto.getFields() );
            if ( list != null ) {
                conversion.setFields( list );
            }
        }

        linkFields( conversion );

        return conversion;
    }

    @Override
    public Conversion toEntity(ConversionPayload ConversionPayload) {
        if ( ConversionPayload == null ) {
            return null;
        }

        Conversion conversion = new Conversion();

        conversion.setFields( conversionFieldDtoListToConversionFieldList1( ConversionPayload.getFields() ) );

        linkFields( conversion );

        return conversion;
    }

    @Override
    public ConversionPayload toDto1(Conversion conversion) {
        if ( conversion == null ) {
            return null;
        }

        List<ConversionPayload.ConversionFieldDto> fields = null;

        fields = conversionFieldListToConversionFieldDtoList1( conversion.getFields() );

        ConversionPayload conversionPayload = new ConversionPayload( fields );

        return conversionPayload;
    }

    @Override
    public Conversion partialUpdate(ConversionPayload ConversionPayload, Conversion conversion) {
        if ( ConversionPayload == null ) {
            return conversion;
        }

        if ( conversion.getFields() != null ) {
            List<ConversionField> list = conversionFieldDtoListToConversionFieldList1( ConversionPayload.getFields() );
            if ( list != null ) {
                conversion.getFields().clear();
                conversion.getFields().addAll( list );
            }
        }
        else {
            List<ConversionField> list = conversionFieldDtoListToConversionFieldList1( ConversionPayload.getFields() );
            if ( list != null ) {
                conversion.setFields( list );
            }
        }

        linkFields( conversion );

        return conversion;
    }

    protected Purchase conversionDtoToPurchase(ConversionDto conversionDto) {
        if ( conversionDto == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setId( conversionDto.getPurchaseId() );

        return purchase;
    }

    protected Production conversionDtoToProduction(ConversionDto conversionDto) {
        if ( conversionDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( conversionDto.getProductionId() );

        return production;
    }

    protected Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto) {
        if ( ingredientDto == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( ingredientDto.getId() );
        ingredient.setName( ingredientDto.getName() );
        ingredient.setUom( ingredientDto.getUom() );

        return ingredient;
    }

    protected ConversionField conversionFieldDtoToConversionField(ConversionDto.ConversionFieldDto conversionFieldDto) {
        if ( conversionFieldDto == null ) {
            return null;
        }

        ConversionField conversionField = new ConversionField();

        conversionField.setId( conversionFieldDto.getId() );
        conversionField.setProductionLitresLost( conversionFieldDto.getProductionLitresLost() );
        conversionField.setKgUsed( conversionFieldDto.getKgUsed() );
        conversionField.setOutPutLitres( conversionFieldDto.getOutPutLitres() );
        conversionField.setUsableLitres( conversionFieldDto.getUsableLitres() );
        conversionField.setLitresPerKg( conversionFieldDto.getLitresPerKg() );
        conversionField.setCostPerLitre( conversionFieldDto.getCostPerLitre() );
        conversionField.setRawBrix( conversionFieldDto.getRawBrix() );
        conversionField.setIngredient( ingredientDtoToIngredient( conversionFieldDto.getIngredient() ) );

        return conversionField;
    }

    protected List<ConversionField> conversionFieldDtoListToConversionFieldList(List<ConversionDto.ConversionFieldDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ConversionField> list1 = new ArrayList<ConversionField>( list.size() );
        for ( ConversionDto.ConversionFieldDto conversionFieldDto : list ) {
            list1.add( conversionFieldDtoToConversionField( conversionFieldDto ) );
        }

        return list1;
    }

    private Long conversionPurchaseId(Conversion conversion) {
        Purchase purchase = conversion.getPurchase();
        if ( purchase == null ) {
            return null;
        }
        return purchase.getId();
    }

    private Long conversionProductionId(Conversion conversion) {
        Production production = conversion.getProduction();
        if ( production == null ) {
            return null;
        }
        return production.getId();
    }

    protected IngredientDto ingredientToIngredientDto(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String uom = null;

        id = ingredient.getId();
        name = ingredient.getName();
        uom = ingredient.getUom();

        IngredientDto ingredientDto = new IngredientDto( id, name, uom );

        return ingredientDto;
    }

    protected ConversionDto.ConversionFieldDto conversionFieldToConversionFieldDto(ConversionField conversionField) {
        if ( conversionField == null ) {
            return null;
        }

        Long id = null;
        double productionLitresLost = 0.0d;
        double kgUsed = 0.0d;
        double outPutLitres = 0.0d;
        double usableLitres = 0.0d;
        double litresPerKg = 0.0d;
        double costPerLitre = 0.0d;
        double rawBrix = 0.0d;
        IngredientDto ingredient = null;

        id = conversionField.getId();
        productionLitresLost = conversionField.getProductionLitresLost();
        kgUsed = conversionField.getKgUsed();
        outPutLitres = conversionField.getOutPutLitres();
        usableLitres = conversionField.getUsableLitres();
        litresPerKg = conversionField.getLitresPerKg();
        costPerLitre = conversionField.getCostPerLitre();
        rawBrix = conversionField.getRawBrix();
        ingredient = ingredientToIngredientDto( conversionField.getIngredient() );

        ConversionDto.ConversionFieldDto conversionFieldDto = new ConversionDto.ConversionFieldDto( id, productionLitresLost, kgUsed, outPutLitres, usableLitres, litresPerKg, costPerLitre, rawBrix, ingredient );

        return conversionFieldDto;
    }

    protected List<ConversionDto.ConversionFieldDto> conversionFieldListToConversionFieldDtoList(List<ConversionField> list) {
        if ( list == null ) {
            return null;
        }

        List<ConversionDto.ConversionFieldDto> list1 = new ArrayList<ConversionDto.ConversionFieldDto>( list.size() );
        for ( ConversionField conversionField : list ) {
            list1.add( conversionFieldToConversionFieldDto( conversionField ) );
        }

        return list1;
    }

    protected void conversionDtoToPurchase1(ConversionDto conversionDto, Purchase mappingTarget) {
        if ( conversionDto == null ) {
            return;
        }

        if ( conversionDto.getPurchaseId() != null ) {
            mappingTarget.setId( conversionDto.getPurchaseId() );
        }
    }

    protected void conversionDtoToProduction1(ConversionDto conversionDto, Production mappingTarget) {
        if ( conversionDto == null ) {
            return;
        }

        if ( conversionDto.getProductionId() != null ) {
            mappingTarget.setId( conversionDto.getProductionId() );
        }
    }

    protected ConversionField conversionFieldDtoToConversionField1(ConversionPayload.ConversionFieldDto conversionFieldDto) {
        if ( conversionFieldDto == null ) {
            return null;
        }

        ConversionField conversionField = new ConversionField();

        conversionField.setProductionLitresLost( conversionFieldDto.getProductionLitresLost() );
        conversionField.setKgUsed( conversionFieldDto.getKgUsed() );
        conversionField.setOutPutLitres( conversionFieldDto.getOutPutLitres() );
        conversionField.setUsableLitres( conversionFieldDto.getUsableLitres() );
        conversionField.setLitresPerKg( conversionFieldDto.getLitresPerKg() );
        conversionField.setCostPerLitre( conversionFieldDto.getCostPerLitre() );
        conversionField.setRawBrix( conversionFieldDto.getRawBrix() );

        return conversionField;
    }

    protected List<ConversionField> conversionFieldDtoListToConversionFieldList1(List<ConversionPayload.ConversionFieldDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ConversionField> list1 = new ArrayList<ConversionField>( list.size() );
        for ( ConversionPayload.ConversionFieldDto conversionFieldDto : list ) {
            list1.add( conversionFieldDtoToConversionField1( conversionFieldDto ) );
        }

        return list1;
    }

    protected ConversionPayload.ConversionFieldDto conversionFieldToConversionFieldDto1(ConversionField conversionField) {
        if ( conversionField == null ) {
            return null;
        }

        double productionLitresLost = 0.0d;
        double kgUsed = 0.0d;
        double outPutLitres = 0.0d;
        double usableLitres = 0.0d;
        double litresPerKg = 0.0d;
        double costPerLitre = 0.0d;
        double rawBrix = 0.0d;

        productionLitresLost = conversionField.getProductionLitresLost();
        kgUsed = conversionField.getKgUsed();
        outPutLitres = conversionField.getOutPutLitres();
        usableLitres = conversionField.getUsableLitres();
        litresPerKg = conversionField.getLitresPerKg();
        costPerLitre = conversionField.getCostPerLitre();
        rawBrix = conversionField.getRawBrix();

        ConversionPayload.ConversionFieldDto conversionFieldDto = new ConversionPayload.ConversionFieldDto( productionLitresLost, kgUsed, outPutLitres, usableLitres, litresPerKg, costPerLitre, rawBrix );

        return conversionFieldDto;
    }

    protected List<ConversionPayload.ConversionFieldDto> conversionFieldListToConversionFieldDtoList1(List<ConversionField> list) {
        if ( list == null ) {
            return null;
        }

        List<ConversionPayload.ConversionFieldDto> list1 = new ArrayList<ConversionPayload.ConversionFieldDto>( list.size() );
        for ( ConversionField conversionField : list ) {
            list1.add( conversionFieldToConversionFieldDto1( conversionField ) );
        }

        return list1;
    }
}
