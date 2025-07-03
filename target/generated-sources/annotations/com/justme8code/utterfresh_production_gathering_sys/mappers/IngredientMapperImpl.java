package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto1;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T19:52:50+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class IngredientMapperImpl implements IngredientMapper {

    @Override
    public Ingredient toEntity(IngredientDto ingredientDto) {
        if ( ingredientDto == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( ingredientDto.getId() );
        ingredient.setName( ingredientDto.getName() );
        ingredient.setUom( ingredientDto.getUom() );

        return ingredient;
    }

    @Override
    public IngredientDto toDto(Ingredient ingredient) {
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

    @Override
    public Ingredient partialUpdate(IngredientDto ingredientDto, Ingredient ingredient) {
        if ( ingredientDto == null ) {
            return ingredient;
        }

        if ( ingredientDto.getId() != null ) {
            ingredient.setId( ingredientDto.getId() );
        }
        if ( ingredientDto.getName() != null ) {
            ingredient.setName( ingredientDto.getName() );
        }
        if ( ingredientDto.getUom() != null ) {
            ingredient.setUom( ingredientDto.getUom() );
        }

        return ingredient;
    }

    @Override
    public Ingredient toEntity(IngredientDto1 ingredientDto1) {
        if ( ingredientDto1 == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( ingredientDto1.getId() );
        ingredient.setName( ingredientDto1.getName() );
        ingredient.setUom( ingredientDto1.getUom() );
        ingredient.setRawMaterials( rawMaterialDto1ListToRawMaterialList( ingredientDto1.getRawMaterials() ) );

        return ingredient;
    }

    @Override
    public IngredientDto1 toDto1(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        List<IngredientDto1.RawMaterialDto1> rawMaterials = null;
        Long id = null;
        String name = null;
        String uom = null;

        rawMaterials = rawMaterialListToRawMaterialDto1List( ingredient.getRawMaterials() );
        id = ingredient.getId();
        name = ingredient.getName();
        uom = ingredient.getUom();

        IngredientDto1 ingredientDto1 = new IngredientDto1( id, name, uom, rawMaterials );

        return ingredientDto1;
    }

    @Override
    public Ingredient partialUpdate(IngredientDto1 ingredientDto1, Ingredient ingredient) {
        if ( ingredientDto1 == null ) {
            return ingredient;
        }

        if ( ingredientDto1.getId() != null ) {
            ingredient.setId( ingredientDto1.getId() );
        }
        if ( ingredientDto1.getName() != null ) {
            ingredient.setName( ingredientDto1.getName() );
        }
        if ( ingredientDto1.getUom() != null ) {
            ingredient.setUom( ingredientDto1.getUom() );
        }
        if ( ingredient.getRawMaterials() != null ) {
            List<RawMaterial> list = rawMaterialDto1ListToRawMaterialList( ingredientDto1.getRawMaterials() );
            if ( list != null ) {
                ingredient.getRawMaterials().clear();
                ingredient.getRawMaterials().addAll( list );
            }
        }
        else {
            List<RawMaterial> list = rawMaterialDto1ListToRawMaterialList( ingredientDto1.getRawMaterials() );
            if ( list != null ) {
                ingredient.setRawMaterials( list );
            }
        }

        return ingredient;
    }

    protected RawMaterial rawMaterialDto1ToRawMaterial(IngredientDto1.RawMaterialDto1 rawMaterialDto1) {
        if ( rawMaterialDto1 == null ) {
            return null;
        }

        RawMaterial rawMaterial = new RawMaterial();

        rawMaterial.setId( rawMaterialDto1.getId() );
        rawMaterial.setName( rawMaterialDto1.getName() );

        return rawMaterial;
    }

    protected List<RawMaterial> rawMaterialDto1ListToRawMaterialList(List<IngredientDto1.RawMaterialDto1> list) {
        if ( list == null ) {
            return null;
        }

        List<RawMaterial> list1 = new ArrayList<RawMaterial>( list.size() );
        for ( IngredientDto1.RawMaterialDto1 rawMaterialDto1 : list ) {
            list1.add( rawMaterialDto1ToRawMaterial( rawMaterialDto1 ) );
        }

        return list1;
    }

    protected IngredientDto1.RawMaterialDto1 rawMaterialToRawMaterialDto1(RawMaterial rawMaterial) {
        if ( rawMaterial == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = rawMaterial.getId();
        name = rawMaterial.getName();

        IngredientDto1.RawMaterialDto1 rawMaterialDto1 = new IngredientDto1.RawMaterialDto1( id, name );

        return rawMaterialDto1;
    }

    protected List<IngredientDto1.RawMaterialDto1> rawMaterialListToRawMaterialDto1List(List<RawMaterial> list) {
        if ( list == null ) {
            return null;
        }

        List<IngredientDto1.RawMaterialDto1> list1 = new ArrayList<IngredientDto1.RawMaterialDto1>( list.size() );
        for ( RawMaterial rawMaterial : list ) {
            list1.add( rawMaterialToRawMaterialDto1( rawMaterial ) );
        }

        return list1;
    }
}
