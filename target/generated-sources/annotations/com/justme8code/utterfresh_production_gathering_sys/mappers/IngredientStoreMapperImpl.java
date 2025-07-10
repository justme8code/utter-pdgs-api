package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.IngredientStoreDashboard;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.IngredientStore;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T10:25:27+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class IngredientStoreMapperImpl implements IngredientStoreMapper {

    @Override
    public IngredientStore toEntity(IngredientStoreDashboard ingredientStoreDashboard) {
        if ( ingredientStoreDashboard == null ) {
            return null;
        }

        IngredientStore ingredientStore = new IngredientStore();

        ingredientStore.setId( ingredientStoreDashboard.getId() );
        ingredientStore.setIngredient( ingredientDto2ToIngredient( ingredientStoreDashboard.getIngredient() ) );
        ingredientStore.setUsableLitresLeft( ingredientStoreDashboard.getUsableLitresLeft() );

        return ingredientStore;
    }

    @Override
    public IngredientStoreDashboard toDto(IngredientStore ingredientStore) {
        if ( ingredientStore == null ) {
            return null;
        }

        Long id = null;
        IngredientStoreDashboard.IngredientDto2 ingredient = null;
        double usableLitresLeft = 0.0d;

        id = ingredientStore.getId();
        ingredient = ingredientToIngredientDto2( ingredientStore.getIngredient() );
        usableLitresLeft = ingredientStore.getUsableLitresLeft();

        IngredientStoreDashboard ingredientStoreDashboard = new IngredientStoreDashboard( id, ingredient, usableLitresLeft );

        return ingredientStoreDashboard;
    }

    @Override
    public IngredientStore partialUpdate(IngredientStoreDashboard ingredientStoreDashboard, IngredientStore ingredientStore) {
        if ( ingredientStoreDashboard == null ) {
            return ingredientStore;
        }

        if ( ingredientStoreDashboard.getId() != null ) {
            ingredientStore.setId( ingredientStoreDashboard.getId() );
        }
        if ( ingredientStoreDashboard.getIngredient() != null ) {
            if ( ingredientStore.getIngredient() == null ) {
                ingredientStore.setIngredient( new Ingredient() );
            }
            ingredientDto2ToIngredient1( ingredientStoreDashboard.getIngredient(), ingredientStore.getIngredient() );
        }
        ingredientStore.setUsableLitresLeft( ingredientStoreDashboard.getUsableLitresLeft() );

        return ingredientStore;
    }

    protected Ingredient ingredientDto2ToIngredient(IngredientStoreDashboard.IngredientDto2 ingredientDto2) {
        if ( ingredientDto2 == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setName( ingredientDto2.getName() );
        ingredient.setUom( ingredientDto2.getUom() );

        return ingredient;
    }

    protected IngredientStoreDashboard.IngredientDto2 ingredientToIngredientDto2(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        String name = null;
        String uom = null;

        name = ingredient.getName();
        uom = ingredient.getUom();

        IngredientStoreDashboard.IngredientDto2 ingredientDto2 = new IngredientStoreDashboard.IngredientDto2( name, uom );

        return ingredientDto2;
    }

    protected void ingredientDto2ToIngredient1(IngredientStoreDashboard.IngredientDto2 ingredientDto2, Ingredient mappingTarget) {
        if ( ingredientDto2 == null ) {
            return;
        }

        if ( ingredientDto2.getName() != null ) {
            mappingTarget.setName( ingredientDto2.getName() );
        }
        if ( ingredientDto2.getUom() != null ) {
            mappingTarget.setUom( ingredientDto2.getUom() );
        }
    }
}
