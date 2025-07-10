package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductionStoreDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.purchase.PurchaseDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductionStore;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterialStore;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T10:25:26+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductionStoreMapperImpl implements ProductionStoreMapper {

    @Override
    public ProductionStore toEntity(ProductionStoreDto productionStoreDto) {
        if ( productionStoreDto == null ) {
            return null;
        }

        ProductionStore productionStore = new ProductionStore();

        productionStore.setProduction( productionStoreDtoToProduction( productionStoreDto ) );
        productionStore.setId( productionStoreDto.getId() );
        productionStore.setIngredientStores( ingredientStoreDtoListToIngredientStoreList( productionStoreDto.getIngredientStores() ) );
        productionStore.setRawMaterialStores( rawMaterialStoreDtoListToRawMaterialStoreList( productionStoreDto.getRawMaterialStores() ) );

        linkIngredientStores( productionStore );

        return productionStore;
    }

    @Override
    public ProductionStoreDto toDto(ProductionStore productionStore) {
        if ( productionStore == null ) {
            return null;
        }

        Long productionId = null;
        List<ProductionStoreDto.IngredientStoreDto> ingredientStores = null;
        List<ProductionStoreDto.RawMaterialStoreDto> rawMaterialStores = null;
        Long id = null;

        productionId = productionStoreProductionId( productionStore );
        ingredientStores = ingredientStoreListToIngredientStoreDtoList( productionStore.getIngredientStores() );
        rawMaterialStores = rawMaterialStoreListToRawMaterialStoreDtoList( productionStore.getRawMaterialStores() );
        id = productionStore.getId();

        ProductionStoreDto productionStoreDto = new ProductionStoreDto( id, productionId, ingredientStores, rawMaterialStores );

        return productionStoreDto;
    }

    @Override
    public ProductionStore partialUpdate(ProductionStoreDto productionStoreDto, ProductionStore productionStore) {
        if ( productionStoreDto == null ) {
            return productionStore;
        }

        if ( productionStore.getProduction() == null ) {
            productionStore.setProduction( new Production() );
        }
        productionStoreDtoToProduction1( productionStoreDto, productionStore.getProduction() );
        if ( productionStoreDto.getId() != null ) {
            productionStore.setId( productionStoreDto.getId() );
        }
        if ( productionStore.getIngredientStores() != null ) {
            List<IngredientStore> list = ingredientStoreDtoListToIngredientStoreList( productionStoreDto.getIngredientStores() );
            if ( list != null ) {
                productionStore.getIngredientStores().clear();
                productionStore.getIngredientStores().addAll( list );
            }
        }
        else {
            List<IngredientStore> list = ingredientStoreDtoListToIngredientStoreList( productionStoreDto.getIngredientStores() );
            if ( list != null ) {
                productionStore.setIngredientStores( list );
            }
        }
        if ( productionStore.getRawMaterialStores() != null ) {
            List<RawMaterialStore> list1 = rawMaterialStoreDtoListToRawMaterialStoreList( productionStoreDto.getRawMaterialStores() );
            if ( list1 != null ) {
                productionStore.getRawMaterialStores().clear();
                productionStore.getRawMaterialStores().addAll( list1 );
            }
        }
        else {
            List<RawMaterialStore> list1 = rawMaterialStoreDtoListToRawMaterialStoreList( productionStoreDto.getRawMaterialStores() );
            if ( list1 != null ) {
                productionStore.setRawMaterialStores( list1 );
            }
        }

        linkIngredientStores( productionStore );

        return productionStore;
    }

    protected Production productionStoreDtoToProduction(ProductionStoreDto productionStoreDto) {
        if ( productionStoreDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( productionStoreDto.getProductionId() );

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

    protected IngredientStore ingredientStoreDtoToIngredientStore(ProductionStoreDto.IngredientStoreDto ingredientStoreDto) {
        if ( ingredientStoreDto == null ) {
            return null;
        }

        IngredientStore ingredientStore = new IngredientStore();

        ingredientStore.setId( ingredientStoreDto.getId() );
        ingredientStore.setIngredient( ingredientDtoToIngredient( ingredientStoreDto.getIngredient() ) );
        ingredientStore.setUsableLitresLeft( ingredientStoreDto.getUsableLitresLeft() );

        return ingredientStore;
    }

    protected List<IngredientStore> ingredientStoreDtoListToIngredientStoreList(List<ProductionStoreDto.IngredientStoreDto> list) {
        if ( list == null ) {
            return null;
        }

        List<IngredientStore> list1 = new ArrayList<IngredientStore>( list.size() );
        for ( ProductionStoreDto.IngredientStoreDto ingredientStoreDto : list ) {
            list1.add( ingredientStoreDtoToIngredientStore( ingredientStoreDto ) );
        }

        return list1;
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

    protected RawMaterialStore rawMaterialStoreDtoToRawMaterialStore(ProductionStoreDto.RawMaterialStoreDto rawMaterialStoreDto) {
        if ( rawMaterialStoreDto == null ) {
            return null;
        }

        RawMaterialStore rawMaterialStore = new RawMaterialStore();

        rawMaterialStore.setId( rawMaterialStoreDto.getId() );
        rawMaterialStore.setRawMaterial( rawMaterialDto1ToRawMaterial( rawMaterialStoreDto.getRawMaterial() ) );
        rawMaterialStore.setTotalUsableQuantity( rawMaterialStoreDto.getTotalUsableQuantity() );
        rawMaterialStore.setTotalUsedQuantity( rawMaterialStoreDto.getTotalUsedQuantity() );

        return rawMaterialStore;
    }

    protected List<RawMaterialStore> rawMaterialStoreDtoListToRawMaterialStoreList(List<ProductionStoreDto.RawMaterialStoreDto> list) {
        if ( list == null ) {
            return null;
        }

        List<RawMaterialStore> list1 = new ArrayList<RawMaterialStore>( list.size() );
        for ( ProductionStoreDto.RawMaterialStoreDto rawMaterialStoreDto : list ) {
            list1.add( rawMaterialStoreDtoToRawMaterialStore( rawMaterialStoreDto ) );
        }

        return list1;
    }

    private Long productionStoreProductionId(ProductionStore productionStore) {
        Production production = productionStore.getProduction();
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

    protected ProductionStoreDto.IngredientStoreDto ingredientStoreToIngredientStoreDto(IngredientStore ingredientStore) {
        if ( ingredientStore == null ) {
            return null;
        }

        Long id = null;
        IngredientDto ingredient = null;
        double usableLitresLeft = 0.0d;

        id = ingredientStore.getId();
        ingredient = ingredientToIngredientDto( ingredientStore.getIngredient() );
        usableLitresLeft = ingredientStore.getUsableLitresLeft();

        ProductionStoreDto.IngredientStoreDto ingredientStoreDto = new ProductionStoreDto.IngredientStoreDto( id, ingredient, usableLitresLeft );

        return ingredientStoreDto;
    }

    protected List<ProductionStoreDto.IngredientStoreDto> ingredientStoreListToIngredientStoreDtoList(List<IngredientStore> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductionStoreDto.IngredientStoreDto> list1 = new ArrayList<ProductionStoreDto.IngredientStoreDto>( list.size() );
        for ( IngredientStore ingredientStore : list ) {
            list1.add( ingredientStoreToIngredientStoreDto( ingredientStore ) );
        }

        return list1;
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

    protected ProductionStoreDto.RawMaterialStoreDto rawMaterialStoreToRawMaterialStoreDto(RawMaterialStore rawMaterialStore) {
        if ( rawMaterialStore == null ) {
            return null;
        }

        Long id = null;
        PurchaseDto.RawMaterialDto1 rawMaterial = null;
        double totalUsableQuantity = 0.0d;
        double totalUsedQuantity = 0.0d;

        id = rawMaterialStore.getId();
        rawMaterial = rawMaterialToRawMaterialDto1( rawMaterialStore.getRawMaterial() );
        totalUsableQuantity = rawMaterialStore.getTotalUsableQuantity();
        totalUsedQuantity = rawMaterialStore.getTotalUsedQuantity();

        ProductionStoreDto.RawMaterialStoreDto rawMaterialStoreDto = new ProductionStoreDto.RawMaterialStoreDto( id, rawMaterial, totalUsableQuantity, totalUsedQuantity );

        return rawMaterialStoreDto;
    }

    protected List<ProductionStoreDto.RawMaterialStoreDto> rawMaterialStoreListToRawMaterialStoreDtoList(List<RawMaterialStore> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductionStoreDto.RawMaterialStoreDto> list1 = new ArrayList<ProductionStoreDto.RawMaterialStoreDto>( list.size() );
        for ( RawMaterialStore rawMaterialStore : list ) {
            list1.add( rawMaterialStoreToRawMaterialStoreDto( rawMaterialStore ) );
        }

        return list1;
    }

    protected void productionStoreDtoToProduction1(ProductionStoreDto productionStoreDto, Production mappingTarget) {
        if ( productionStoreDto == null ) {
            return;
        }

        if ( productionStoreDto.getProductionId() != null ) {
            mappingTarget.setId( productionStoreDto.getProductionId() );
        }
    }
}
