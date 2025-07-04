package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.PMOutputLessDetail;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixOutputDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMixIngredient;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.Ingredient;
import java.time.LocalDateTime;
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
public class ProductMixOutputMapperImpl implements ProductMixOutputMapper {

    @Override
    public ProductMix toEntity(ProductMixOutputDto productMixOutputDto) {
        if ( productMixOutputDto == null ) {
            return null;
        }

        ProductMix productMix = new ProductMix();

        productMix.setCreatedAt( productMixOutputDto.getCreatedAt() );
        productMix.setId( productMixOutputDto.getId() );
        productMix.setProduct( productDto1ToProduct( productMixOutputDto.getProduct() ) );
        productMix.setProductMixIngredients( productMixIngredientDto1ListToProductMixIngredientList( productMixOutputDto.getProductMixIngredients() ) );
        productMix.setProductCount( productMixOutputDto.getProductCount() );
        productMix.setTotalLitersUsed( productMixOutputDto.getTotalLitersUsed() );
        productMix.setQty( productMixOutputDto.getQty() );
        productMix.setBrixOnDiluent( productMixOutputDto.getBrixOnDiluent() );
        productMix.setInitialBrix( productMixOutputDto.getInitialBrix() );
        productMix.setFinalBrix( productMixOutputDto.getFinalBrix() );
        productMix.setInitialPH( productMixOutputDto.getInitialPH() );
        productMix.setFinalPH( productMixOutputDto.getFinalPH() );

        return productMix;
    }

    @Override
    public ProductMixOutputDto toDto(ProductMix productMix) {
        if ( productMix == null ) {
            return null;
        }

        List<ProductMixOutputDto.ProductMixIngredientDto1> productMixIngredients = null;
        LocalDateTime createdAt = null;
        Long id = null;
        ProductMixOutputDto.ProductDto1 product = null;
        Double productCount = null;
        Double totalLitersUsed = null;
        Integer qty = null;
        Double brixOnDiluent = null;
        Double initialBrix = null;
        Double finalBrix = null;
        Double initialPH = null;
        Double finalPH = null;

        productMixIngredients = productMixIngredientListToProductMixIngredientDto1List( productMix.getProductMixIngredients() );
        createdAt = productMix.getCreatedAt();
        id = productMix.getId();
        product = productToProductDto1( productMix.getProduct() );
        productCount = productMix.getProductCount();
        totalLitersUsed = productMix.getTotalLitersUsed();
        qty = productMix.getQty();
        brixOnDiluent = productMix.getBrixOnDiluent();
        initialBrix = productMix.getInitialBrix();
        finalBrix = productMix.getFinalBrix();
        initialPH = productMix.getInitialPH();
        finalPH = productMix.getFinalPH();

        ProductMixOutputDto productMixOutputDto = new ProductMixOutputDto( createdAt, id, product, productMixIngredients, productCount, totalLitersUsed, qty, brixOnDiluent, initialBrix, finalBrix, initialPH, finalPH );

        return productMixOutputDto;
    }

    @Override
    public ProductMix partialUpdate(ProductMixOutputDto productMixOutputDto, ProductMix productMix) {
        if ( productMixOutputDto == null ) {
            return productMix;
        }

        if ( productMixOutputDto.getCreatedAt() != null ) {
            productMix.setCreatedAt( productMixOutputDto.getCreatedAt() );
        }
        if ( productMixOutputDto.getId() != null ) {
            productMix.setId( productMixOutputDto.getId() );
        }
        if ( productMixOutputDto.getProduct() != null ) {
            if ( productMix.getProduct() == null ) {
                productMix.setProduct( new Product() );
            }
            productDto1ToProduct1( productMixOutputDto.getProduct(), productMix.getProduct() );
        }
        if ( productMix.getProductMixIngredients() != null ) {
            List<ProductMixIngredient> list = productMixIngredientDto1ListToProductMixIngredientList( productMixOutputDto.getProductMixIngredients() );
            if ( list != null ) {
                productMix.getProductMixIngredients().clear();
                productMix.getProductMixIngredients().addAll( list );
            }
        }
        else {
            List<ProductMixIngredient> list = productMixIngredientDto1ListToProductMixIngredientList( productMixOutputDto.getProductMixIngredients() );
            if ( list != null ) {
                productMix.setProductMixIngredients( list );
            }
        }
        if ( productMixOutputDto.getProductCount() != null ) {
            productMix.setProductCount( productMixOutputDto.getProductCount() );
        }
        if ( productMixOutputDto.getTotalLitersUsed() != null ) {
            productMix.setTotalLitersUsed( productMixOutputDto.getTotalLitersUsed() );
        }
        if ( productMixOutputDto.getQty() != null ) {
            productMix.setQty( productMixOutputDto.getQty() );
        }
        if ( productMixOutputDto.getBrixOnDiluent() != null ) {
            productMix.setBrixOnDiluent( productMixOutputDto.getBrixOnDiluent() );
        }
        if ( productMixOutputDto.getInitialBrix() != null ) {
            productMix.setInitialBrix( productMixOutputDto.getInitialBrix() );
        }
        if ( productMixOutputDto.getFinalBrix() != null ) {
            productMix.setFinalBrix( productMixOutputDto.getFinalBrix() );
        }
        if ( productMixOutputDto.getInitialPH() != null ) {
            productMix.setInitialPH( productMixOutputDto.getInitialPH() );
        }
        if ( productMixOutputDto.getFinalPH() != null ) {
            productMix.setFinalPH( productMixOutputDto.getFinalPH() );
        }

        return productMix;
    }

    @Override
    public ProductMix toEntity(PMOutputLessDetail PMOutputLessDetail) {
        if ( PMOutputLessDetail == null ) {
            return null;
        }

        ProductMix productMix = new ProductMix();

        productMix.setProduct( pMOutputLessDetailToProduct( PMOutputLessDetail ) );
        productMix.setId( PMOutputLessDetail.getId() );

        return productMix;
    }

    @Override
    public PMOutputLessDetail toDto1(ProductMix productMix) {
        if ( productMix == null ) {
            return null;
        }

        String productName = null;
        Long id = null;

        productName = productMixProductName( productMix );
        id = productMix.getId();

        PMOutputLessDetail pMOutputLessDetail = new PMOutputLessDetail( id, productName );

        return pMOutputLessDetail;
    }

    @Override
    public ProductMix partialUpdate(PMOutputLessDetail PMOutputLessDetail, ProductMix productMix) {
        if ( PMOutputLessDetail == null ) {
            return productMix;
        }

        if ( productMix.getProduct() == null ) {
            productMix.setProduct( new Product() );
        }
        pMOutputLessDetailToProduct1( PMOutputLessDetail, productMix.getProduct() );
        if ( PMOutputLessDetail.getId() != null ) {
            productMix.setId( PMOutputLessDetail.getId() );
        }

        return productMix;
    }

    protected Product productDto1ToProduct(ProductMixOutputDto.ProductDto1 productDto1) {
        if ( productDto1 == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto1.getId() );
        product.setName( productDto1.getName() );
        product.setUnitOfMeasure( productDto1.getUnitOfMeasure() );

        return product;
    }

    protected Ingredient ingredientDto2ToIngredient(ProductMixOutputDto.ProductMixIngredientDto1.IngredientDto2 ingredientDto2) {
        if ( ingredientDto2 == null ) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId( ingredientDto2.getId() );
        ingredient.setName( ingredientDto2.getName() );

        return ingredient;
    }

    protected ProductMixIngredient productMixIngredientDto1ToProductMixIngredient(ProductMixOutputDto.ProductMixIngredientDto1 productMixIngredientDto1) {
        if ( productMixIngredientDto1 == null ) {
            return null;
        }

        ProductMixIngredient productMixIngredient = new ProductMixIngredient();

        productMixIngredient.setId( productMixIngredientDto1.getId() );
        productMixIngredient.setIngredient( ingredientDto2ToIngredient( productMixIngredientDto1.getIngredient() ) );
        productMixIngredient.setLitresUsed( productMixIngredientDto1.getLitresUsed() );

        return productMixIngredient;
    }

    protected List<ProductMixIngredient> productMixIngredientDto1ListToProductMixIngredientList(List<ProductMixOutputDto.ProductMixIngredientDto1> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductMixIngredient> list1 = new ArrayList<ProductMixIngredient>( list.size() );
        for ( ProductMixOutputDto.ProductMixIngredientDto1 productMixIngredientDto1 : list ) {
            list1.add( productMixIngredientDto1ToProductMixIngredient( productMixIngredientDto1 ) );
        }

        return list1;
    }

    protected ProductMixOutputDto.ProductMixIngredientDto1.IngredientDto2 ingredientToIngredientDto2(Ingredient ingredient) {
        if ( ingredient == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = ingredient.getId();
        name = ingredient.getName();

        ProductMixOutputDto.ProductMixIngredientDto1.IngredientDto2 ingredientDto2 = new ProductMixOutputDto.ProductMixIngredientDto1.IngredientDto2( id, name );

        return ingredientDto2;
    }

    protected ProductMixOutputDto.ProductMixIngredientDto1 productMixIngredientToProductMixIngredientDto1(ProductMixIngredient productMixIngredient) {
        if ( productMixIngredient == null ) {
            return null;
        }

        Long id = null;
        ProductMixOutputDto.ProductMixIngredientDto1.IngredientDto2 ingredient = null;
        Double litresUsed = null;

        id = productMixIngredient.getId();
        ingredient = ingredientToIngredientDto2( productMixIngredient.getIngredient() );
        litresUsed = productMixIngredient.getLitresUsed();

        ProductMixOutputDto.ProductMixIngredientDto1 productMixIngredientDto1 = new ProductMixOutputDto.ProductMixIngredientDto1( id, ingredient, litresUsed );

        return productMixIngredientDto1;
    }

    protected List<ProductMixOutputDto.ProductMixIngredientDto1> productMixIngredientListToProductMixIngredientDto1List(List<ProductMixIngredient> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductMixOutputDto.ProductMixIngredientDto1> list1 = new ArrayList<ProductMixOutputDto.ProductMixIngredientDto1>( list.size() );
        for ( ProductMixIngredient productMixIngredient : list ) {
            list1.add( productMixIngredientToProductMixIngredientDto1( productMixIngredient ) );
        }

        return list1;
    }

    protected ProductMixOutputDto.ProductDto1 productToProductDto1(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String unitOfMeasure = null;

        id = product.getId();
        name = product.getName();
        unitOfMeasure = product.getUnitOfMeasure();

        ProductMixOutputDto.ProductDto1 productDto1 = new ProductMixOutputDto.ProductDto1( id, name, unitOfMeasure );

        return productDto1;
    }

    protected void productDto1ToProduct1(ProductMixOutputDto.ProductDto1 productDto1, Product mappingTarget) {
        if ( productDto1 == null ) {
            return;
        }

        if ( productDto1.getId() != null ) {
            mappingTarget.setId( productDto1.getId() );
        }
        if ( productDto1.getName() != null ) {
            mappingTarget.setName( productDto1.getName() );
        }
        if ( productDto1.getUnitOfMeasure() != null ) {
            mappingTarget.setUnitOfMeasure( productDto1.getUnitOfMeasure() );
        }
    }

    protected Product pMOutputLessDetailToProduct(PMOutputLessDetail pMOutputLessDetail) {
        if ( pMOutputLessDetail == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( pMOutputLessDetail.getProductName() );

        return product;
    }

    private String productMixProductName(ProductMix productMix) {
        Product product = productMix.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }

    protected void pMOutputLessDetailToProduct1(PMOutputLessDetail pMOutputLessDetail, Product mappingTarget) {
        if ( pMOutputLessDetail == null ) {
            return;
        }

        if ( pMOutputLessDetail.getProductName() != null ) {
            mappingTarget.setName( pMOutputLessDetail.getProductName() );
        }
    }
}
