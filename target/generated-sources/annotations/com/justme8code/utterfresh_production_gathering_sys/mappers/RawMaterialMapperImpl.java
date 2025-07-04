package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.RawMaterialDto;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T15:20:15+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class RawMaterialMapperImpl implements RawMaterialMapper {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public RawMaterial toEntity(RawMaterialDto rawMaterialDto) {
        if ( rawMaterialDto == null ) {
            return null;
        }

        RawMaterial rawMaterial = new RawMaterial();

        rawMaterial.setId( rawMaterialDto.getId() );
        rawMaterial.setName( rawMaterialDto.getName() );
        rawMaterial.setUom( rawMaterialDto.getUom() );
        rawMaterial.setIngredients( ingredientDtoListToIngredientList( rawMaterialDto.getIngredients() ) );

        return rawMaterial;
    }

    @Override
    public RawMaterialDto toDto(RawMaterial rawMaterial) {
        if ( rawMaterial == null ) {
            return null;
        }

        List<IngredientDto> ingredients = null;
        Long id = null;
        String name = null;
        String uom = null;

        ingredients = ingredientListToIngredientDtoList( rawMaterial.getIngredients() );
        id = rawMaterial.getId();
        name = rawMaterial.getName();
        uom = rawMaterial.getUom();

        RawMaterialDto rawMaterialDto = new RawMaterialDto( id, name, uom, ingredients );

        return rawMaterialDto;
    }

    @Override
    public RawMaterial partialUpdate(RawMaterialDto rawMaterialDto, RawMaterial rawMaterial) {
        if ( rawMaterialDto == null ) {
            return rawMaterial;
        }

        if ( rawMaterialDto.getId() != null ) {
            rawMaterial.setId( rawMaterialDto.getId() );
        }
        if ( rawMaterialDto.getName() != null ) {
            rawMaterial.setName( rawMaterialDto.getName() );
        }
        if ( rawMaterialDto.getUom() != null ) {
            rawMaterial.setUom( rawMaterialDto.getUom() );
        }
        if ( rawMaterial.getIngredients() != null ) {
            List<Ingredient> list = ingredientDtoListToIngredientList( rawMaterialDto.getIngredients() );
            if ( list != null ) {
                rawMaterial.getIngredients().clear();
                rawMaterial.getIngredients().addAll( list );
            }
        }
        else {
            List<Ingredient> list = ingredientDtoListToIngredientList( rawMaterialDto.getIngredients() );
            if ( list != null ) {
                rawMaterial.setIngredients( list );
            }
        }

        return rawMaterial;
    }

    protected List<Ingredient> ingredientDtoListToIngredientList(List<IngredientDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Ingredient> list1 = new ArrayList<Ingredient>( list.size() );
        for ( IngredientDto ingredientDto : list ) {
            list1.add( ingredientMapper.toEntity( ingredientDto ) );
        }

        return list1;
    }

    protected List<IngredientDto> ingredientListToIngredientDtoList(List<Ingredient> list) {
        if ( list == null ) {
            return null;
        }

        List<IngredientDto> list1 = new ArrayList<IngredientDto>( list.size() );
        for ( Ingredient ingredient : list ) {
            list1.add( ingredientMapper.toDto( ingredient ) );
        }

        return list1;
    }
}
