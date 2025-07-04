package com.justme8code.utterfresh_production_gathering_sys.models.event;

import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T15:20:15+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class Conversion$ProductMixIngredientMapperImpl implements Conversion.ProductMixIngredientMapper {

    @Override
    public ProductMixIngredient toEntity(ProductMixIngredientDto productMixIngredientDto) {
        if ( productMixIngredientDto == null ) {
            return null;
        }

        ProductMixIngredient productMixIngredient = new ProductMixIngredient();

        productMixIngredient.setIngredient( productMixIngredientDtoToIngredient( productMixIngredientDto ) );
        productMixIngredient.setId( productMixIngredientDto.getId() );
        productMixIngredient.setLitresUsed( productMixIngredientDto.getLitresUsed() );

        return productMixIngredient;
    }

    @Override
    public ProductMixIngredientDto toDto(ProductMixIngredient productMixIngredient) {
        if ( productMixIngredient == null ) {
            return null;
        }

        Long ingredientId = null;
        Long id = null;
        Double litresUsed = null;

        ingredientId = productMixIngredientIngredientId( productMixIngredient );
        id = productMixIngredient.getId();
        litresUsed = productMixIngredient.getLitresUsed();

        ProductMixIngredientDto productMixIngredientDto = new ProductMixIngredientDto( id, ingredientId, litresUsed );

        return productMixIngredientDto;
    }

    @Override
    public ProductMixIngredient partialUpdate(ProductMixIngredientDto ingredientUsageDto, ProductMixIngredient productMixIngredient) {
        if ( ingredientUsageDto == null ) {
            return productMixIngredient;
        }

        if ( productMixIngredient.getIngredient() == null ) {
            productMixIngredient.setIngredient( new Ingredient() );
        }
        productMixIngredientDtoToIngredient1( ingredientUsageDto, productMixIngredient.getIngredient() );
        if ( ingredientUsageDto.getId() != null ) {
            productMixIngredient.setId( ingredientUsageDto.getId() );
        }
        if ( ingredientUsageDto.getLitresUsed() != null ) {
            productMixIngredient.setLitresUsed( ingredientUsageDto.getLitresUsed() );
        }

        return productMixIngredient;
    }

    protected Ingredient productMixIngredientDtoToIngredient(ProductMixIngredientDto productMixIngredientDto) {
        if ( productMixIngredientDto == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( productMixIngredientDto.getIngredientId() );

        return ingredient;
    }

    private Long productMixIngredientIngredientId(ProductMixIngredient productMixIngredient) {
        Ingredient ingredient = productMixIngredient.getIngredient();
        if ( ingredient == null ) {
            return null;
        }
        return ingredient.getId();
    }

    protected void productMixIngredientDtoToIngredient1(ProductMixIngredientDto productMixIngredientDto, Ingredient mappingTarget) {
        if ( productMixIngredientDto == null ) {
            return;
        }

        if ( productMixIngredientDto.getIngredientId() != null ) {
            mappingTarget.setId( productMixIngredientDto.getIngredientId() );
        }
    }
}
