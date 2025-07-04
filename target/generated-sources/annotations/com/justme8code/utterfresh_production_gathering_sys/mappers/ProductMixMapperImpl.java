package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.dashboard.ProductMixDashboardData;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.productmix.ProductMixIngredientDto;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Conversion;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Product;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMixIngredient;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
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
public class ProductMixMapperImpl implements ProductMixMapper {

    @Autowired
    private Conversion.ProductMixIngredientMapper productMixIngredientMapper;

    @Override
    public ProductMix toEntity(ProductMixDto productMixDto) {
        if ( productMixDto == null ) {
            return null;
        }

        ProductMix productMix = new ProductMix();

        productMix.setProduct( productMixDtoToProduct( productMixDto ) );
        productMix.setProduction( productMixDtoToProduction( productMixDto ) );
        productMix.setId( productMixDto.getId() );
        productMix.setProductMixIngredients( productMixIngredientDtoListToProductMixIngredientList( productMixDto.getProductMixIngredients() ) );
        productMix.setProductCount( productMixDto.getProductCount() );
        productMix.setTotalLitersUsed( productMixDto.getTotalLitersUsed() );
        productMix.setBrixOnDiluent( productMixDto.getBrixOnDiluent() );
        productMix.setInitialBrix( productMixDto.getInitialBrix() );
        productMix.setFinalBrix( productMixDto.getFinalBrix() );
        productMix.setInitialPH( productMixDto.getInitialPH() );
        productMix.setFinalPH( productMixDto.getFinalPH() );

        return productMix;
    }

    @Override
    public ProductMixDto toDto(ProductMix productMix) {
        if ( productMix == null ) {
            return null;
        }

        Long productId = null;
        Long productionId = null;
        List<ProductMixIngredientDto> productMixIngredients = null;
        Long id = null;
        Double productCount = null;
        Double totalLitersUsed = null;
        Double brixOnDiluent = null;
        Double initialBrix = null;
        Double finalBrix = null;
        Double initialPH = null;
        Double finalPH = null;

        productId = productMixProductId( productMix );
        productionId = productMixProductionId( productMix );
        productMixIngredients = productMixIngredientListToProductMixIngredientDtoList( productMix.getProductMixIngredients() );
        id = productMix.getId();
        productCount = productMix.getProductCount();
        totalLitersUsed = productMix.getTotalLitersUsed();
        brixOnDiluent = productMix.getBrixOnDiluent();
        initialBrix = productMix.getInitialBrix();
        finalBrix = productMix.getFinalBrix();
        initialPH = productMix.getInitialPH();
        finalPH = productMix.getFinalPH();

        ProductMixDto productMixDto = new ProductMixDto( id, productionId, productId, productMixIngredients, productCount, totalLitersUsed, brixOnDiluent, initialBrix, finalBrix, initialPH, finalPH );

        return productMixDto;
    }

    @Override
    public ProductMix partialUpdate(ProductMixDto productMixDto, ProductMix productMix) {
        if ( productMixDto == null ) {
            return productMix;
        }

        if ( productMix.getProduct() == null ) {
            productMix.setProduct( new Product() );
        }
        productMixDtoToProduct1( productMixDto, productMix.getProduct() );
        if ( productMix.getProduction() == null ) {
            productMix.setProduction( new Production() );
        }
        productMixDtoToProduction1( productMixDto, productMix.getProduction() );
        if ( productMixDto.getId() != null ) {
            productMix.setId( productMixDto.getId() );
        }
        if ( productMix.getProductMixIngredients() != null ) {
            List<ProductMixIngredient> list = productMixIngredientDtoListToProductMixIngredientList( productMixDto.getProductMixIngredients() );
            if ( list != null ) {
                productMix.getProductMixIngredients().clear();
                productMix.getProductMixIngredients().addAll( list );
            }
        }
        else {
            List<ProductMixIngredient> list = productMixIngredientDtoListToProductMixIngredientList( productMixDto.getProductMixIngredients() );
            if ( list != null ) {
                productMix.setProductMixIngredients( list );
            }
        }
        if ( productMixDto.getProductCount() != null ) {
            productMix.setProductCount( productMixDto.getProductCount() );
        }
        if ( productMixDto.getTotalLitersUsed() != null ) {
            productMix.setTotalLitersUsed( productMixDto.getTotalLitersUsed() );
        }
        if ( productMixDto.getBrixOnDiluent() != null ) {
            productMix.setBrixOnDiluent( productMixDto.getBrixOnDiluent() );
        }
        if ( productMixDto.getInitialBrix() != null ) {
            productMix.setInitialBrix( productMixDto.getInitialBrix() );
        }
        if ( productMixDto.getFinalBrix() != null ) {
            productMix.setFinalBrix( productMixDto.getFinalBrix() );
        }
        if ( productMixDto.getInitialPH() != null ) {
            productMix.setInitialPH( productMixDto.getInitialPH() );
        }
        if ( productMixDto.getFinalPH() != null ) {
            productMix.setFinalPH( productMixDto.getFinalPH() );
        }

        return productMix;
    }

    @Override
    public ProductMix toEntity(ProductMixDashboardData productMixDashboardData) {
        if ( productMixDashboardData == null ) {
            return null;
        }

        ProductMix productMix = new ProductMix();

        productMix.setProduct( productMixDashboardDataToProduct( productMixDashboardData ) );
        productMix.setId( productMixDashboardData.getId() );
        productMix.setProduction( productionDtoToProduction( productMixDashboardData.getProduction() ) );
        productMix.setProductCount( productMixDashboardData.getProductCount() );
        productMix.setTotalLitersUsed( productMixDashboardData.getTotalLitersUsed() );

        return productMix;
    }

    @Override
    public ProductMixDashboardData toDto1(ProductMix productMix) {
        if ( productMix == null ) {
            return null;
        }

        String productName = null;
        Long id = null;
        ProductMixDashboardData.ProductionDto production = null;
        Double productCount = null;
        Double totalLitersUsed = null;

        productName = productMixProductName( productMix );
        id = productMix.getId();
        production = productionToProductionDto( productMix.getProduction() );
        productCount = productMix.getProductCount();
        totalLitersUsed = productMix.getTotalLitersUsed();

        ProductMixDashboardData productMixDashboardData = new ProductMixDashboardData( id, production, productName, productCount, totalLitersUsed );

        return productMixDashboardData;
    }

    @Override
    public ProductMix partialUpdate(ProductMixDashboardData productMixDashboardData, ProductMix productMix) {
        if ( productMixDashboardData == null ) {
            return productMix;
        }

        if ( productMix.getProduct() == null ) {
            productMix.setProduct( new Product() );
        }
        productMixDashboardDataToProduct1( productMixDashboardData, productMix.getProduct() );
        if ( productMixDashboardData.getId() != null ) {
            productMix.setId( productMixDashboardData.getId() );
        }
        if ( productMixDashboardData.getProduction() != null ) {
            if ( productMix.getProduction() == null ) {
                productMix.setProduction( new Production() );
            }
            productionDtoToProduction1( productMixDashboardData.getProduction(), productMix.getProduction() );
        }
        if ( productMixDashboardData.getProductCount() != null ) {
            productMix.setProductCount( productMixDashboardData.getProductCount() );
        }
        if ( productMixDashboardData.getTotalLitersUsed() != null ) {
            productMix.setTotalLitersUsed( productMixDashboardData.getTotalLitersUsed() );
        }

        return productMix;
    }

    protected Product productMixDtoToProduct(ProductMixDto productMixDto) {
        if ( productMixDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productMixDto.getProductId() );

        return product;
    }

    protected Production productMixDtoToProduction(ProductMixDto productMixDto) {
        if ( productMixDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( productMixDto.getProductionId() );

        return production;
    }

    protected List<ProductMixIngredient> productMixIngredientDtoListToProductMixIngredientList(List<ProductMixIngredientDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductMixIngredient> list1 = new ArrayList<ProductMixIngredient>( list.size() );
        for ( ProductMixIngredientDto productMixIngredientDto : list ) {
            list1.add( productMixIngredientMapper.toEntity( productMixIngredientDto ) );
        }

        return list1;
    }

    private Long productMixProductId(ProductMix productMix) {
        Product product = productMix.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private Long productMixProductionId(ProductMix productMix) {
        Production production = productMix.getProduction();
        if ( production == null ) {
            return null;
        }
        return production.getId();
    }

    protected List<ProductMixIngredientDto> productMixIngredientListToProductMixIngredientDtoList(List<ProductMixIngredient> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductMixIngredientDto> list1 = new ArrayList<ProductMixIngredientDto>( list.size() );
        for ( ProductMixIngredient productMixIngredient : list ) {
            list1.add( productMixIngredientMapper.toDto( productMixIngredient ) );
        }

        return list1;
    }

    protected void productMixDtoToProduct1(ProductMixDto productMixDto, Product mappingTarget) {
        if ( productMixDto == null ) {
            return;
        }

        if ( productMixDto.getProductId() != null ) {
            mappingTarget.setId( productMixDto.getProductId() );
        }
    }

    protected void productMixDtoToProduction1(ProductMixDto productMixDto, Production mappingTarget) {
        if ( productMixDto == null ) {
            return;
        }

        if ( productMixDto.getProductionId() != null ) {
            mappingTarget.setId( productMixDto.getProductionId() );
        }
    }

    protected Product productMixDashboardDataToProduct(ProductMixDashboardData productMixDashboardData) {
        if ( productMixDashboardData == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productMixDashboardData.getProductName() );

        return product;
    }

    protected Production productionDtoToProduction(ProductMixDashboardData.ProductionDto productionDto) {
        if ( productionDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( productionDto.getId() );
        production.setName( productionDto.getName() );

        return production;
    }

    private String productMixProductName(ProductMix productMix) {
        Product product = productMix.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }

    protected ProductMixDashboardData.ProductionDto productionToProductionDto(Production production) {
        if ( production == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = production.getId();
        name = production.getName();

        ProductMixDashboardData.ProductionDto productionDto = new ProductMixDashboardData.ProductionDto( id, name );

        return productionDto;
    }

    protected void productMixDashboardDataToProduct1(ProductMixDashboardData productMixDashboardData, Product mappingTarget) {
        if ( productMixDashboardData == null ) {
            return;
        }

        if ( productMixDashboardData.getProductName() != null ) {
            mappingTarget.setName( productMixDashboardData.getProductName() );
        }
    }

    protected void productionDtoToProduction1(ProductMixDashboardData.ProductionDto productionDto, Production mappingTarget) {
        if ( productionDto == null ) {
            return;
        }

        if ( productionDto.getId() != null ) {
            mappingTarget.setId( productionDto.getId() );
        }
        if ( productionDto.getName() != null ) {
            mappingTarget.setName( productionDto.getName() );
        }
    }
}
