package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.IngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.production.ProductDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T15:20:15+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );
        product.setUnitOfMeasure( productDto.getUnitOfMeasure() );
        product.setTotalProductMixCount( productDto.getTotalProductMixCount() );
        product.setIngredients( ingredientDtoListToIngredientList( productDto.getIngredients() ) );

        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        List<IngredientDto> ingredients = null;
        Long id = null;
        String name = null;
        String description = null;
        String unitOfMeasure = null;
        Long totalProductMixCount = null;

        ingredients = ingredientListToIngredientDtoList( product.getIngredients() );
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        unitOfMeasure = product.getUnitOfMeasure();
        totalProductMixCount = product.getTotalProductMixCount();

        ProductDto productDto = new ProductDto( id, name, description, unitOfMeasure, totalProductMixCount, ingredients );

        return productDto;
    }

    @Override
    public Product partialUpdate(ProductDto productDto, Product product) {
        if ( productDto == null ) {
            return product;
        }

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        if ( productDto.getName() != null ) {
            product.setName( productDto.getName() );
        }
        if ( productDto.getDescription() != null ) {
            product.setDescription( productDto.getDescription() );
        }
        if ( productDto.getUnitOfMeasure() != null ) {
            product.setUnitOfMeasure( productDto.getUnitOfMeasure() );
        }
        if ( productDto.getTotalProductMixCount() != null ) {
            product.setTotalProductMixCount( productDto.getTotalProductMixCount() );
        }
        if ( product.getIngredients() != null ) {
            List<Ingredient> list = ingredientDtoListToIngredientList( productDto.getIngredients() );
            if ( list != null ) {
                product.getIngredients().clear();
                product.getIngredients().addAll( list );
            }
        }
        else {
            List<Ingredient> list = ingredientDtoListToIngredientList( productDto.getIngredients() );
            if ( list != null ) {
                product.setIngredients( list );
            }
        }

        return product;
    }

    @Override
    public Product toEntity(ProductDashboardData productDashboardData) {
        if ( productDashboardData == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDashboardData.getId() );
        product.setName( productDashboardData.getName() );
        product.setTotalProductMixCount( productDashboardData.getTotalProductMixCount() );

        return product;
    }

    @Override
    public ProductDashboardData toDto1(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Long totalProductMixCount = null;

        id = product.getId();
        name = product.getName();
        totalProductMixCount = product.getTotalProductMixCount();

        ProductDashboardData productDashboardData = new ProductDashboardData( id, name, totalProductMixCount );

        return productDashboardData;
    }

    @Override
    public Product partialUpdate(ProductDashboardData productDashboardData, Product product) {
        if ( productDashboardData == null ) {
            return product;
        }

        if ( productDashboardData.getId() != null ) {
            product.setId( productDashboardData.getId() );
        }
        if ( productDashboardData.getName() != null ) {
            product.setName( productDashboardData.getName() );
        }
        if ( productDashboardData.getTotalProductMixCount() != null ) {
            product.setTotalProductMixCount( productDashboardData.getTotalProductMixCount() );
        }

        return product;
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

    protected List<Ingredient> ingredientDtoListToIngredientList(List<IngredientDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Ingredient> list1 = new ArrayList<Ingredient>( list.size() );
        for ( IngredientDto ingredientDto : list ) {
            list1.add( ingredientDtoToIngredient( ingredientDto ) );
        }

        return list1;
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

    protected List<IngredientDto> ingredientListToIngredientDtoList(List<Ingredient> list) {
        if ( list == null ) {
            return null;
        }

        List<IngredientDto> list1 = new ArrayList<IngredientDto>( list.size() );
        for ( Ingredient ingredient : list ) {
            list1.add( ingredientToIngredientDto( ingredient ) );
        }

        return list1;
    }
}
