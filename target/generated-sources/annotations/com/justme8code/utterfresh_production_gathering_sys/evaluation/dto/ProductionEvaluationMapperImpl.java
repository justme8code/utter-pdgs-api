package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Taste;
import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-03T19:52:50+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class ProductionEvaluationMapperImpl implements ProductionEvaluationMapper {

    @Override
    public ProductionEvaluation toEntity(ProductionEvaluationDto productionEvaluationDto) {
        if ( productionEvaluationDto == null ) {
            return null;
        }

        ProductionEvaluation productionEvaluation = new ProductionEvaluation();

        productionEvaluation.setId( productionEvaluationDto.getId() );
        productionEvaluation.setProductMix( productMixDtoToProductMix( productionEvaluationDto.getProductMix() ) );
        productionEvaluation.setTaste( productionEvaluationDto.getTaste() );
        productionEvaluation.setAfterTaste( productionEvaluationDto.getAfterTaste() );
        productionEvaluation.setViscosity( productionEvaluationDto.getViscosity() );
        productionEvaluation.setComment( productionEvaluationDto.getComment() );
        productionEvaluation.setRelease( productionEvaluationDto.isRelease() );

        return productionEvaluation;
    }

    @Override
    public ProductionEvaluationDto toDto(ProductionEvaluation productionEvaluation) {
        if ( productionEvaluation == null ) {
            return null;
        }

        Long id = null;
        ProductionEvaluationDto.ProductMixDto productMix = null;
        Taste taste = null;
        Taste afterTaste = null;
        Taste viscosity = null;
        String comment = null;
        boolean release = false;

        id = productionEvaluation.getId();
        productMix = productMixToProductMixDto( productionEvaluation.getProductMix() );
        taste = productionEvaluation.getTaste();
        afterTaste = productionEvaluation.getAfterTaste();
        viscosity = productionEvaluation.getViscosity();
        comment = productionEvaluation.getComment();
        release = productionEvaluation.isRelease();

        ProductionEvaluationDto productionEvaluationDto = new ProductionEvaluationDto( id, productMix, taste, afterTaste, viscosity, comment, release );

        return productionEvaluationDto;
    }

    @Override
    public ProductionEvaluation partialUpdate(ProductionEvaluationDto productionEvaluationDto, ProductionEvaluation productionEvaluation) {
        if ( productionEvaluationDto == null ) {
            return productionEvaluation;
        }

        if ( productionEvaluationDto.getId() != null ) {
            productionEvaluation.setId( productionEvaluationDto.getId() );
        }
        if ( productionEvaluationDto.getProductMix() != null ) {
            if ( productionEvaluation.getProductMix() == null ) {
                productionEvaluation.setProductMix( new ProductMix() );
            }
            productMixDtoToProductMix1( productionEvaluationDto.getProductMix(), productionEvaluation.getProductMix() );
        }
        if ( productionEvaluationDto.getTaste() != null ) {
            productionEvaluation.setTaste( productionEvaluationDto.getTaste() );
        }
        if ( productionEvaluationDto.getAfterTaste() != null ) {
            productionEvaluation.setAfterTaste( productionEvaluationDto.getAfterTaste() );
        }
        if ( productionEvaluationDto.getViscosity() != null ) {
            productionEvaluation.setViscosity( productionEvaluationDto.getViscosity() );
        }
        if ( productionEvaluationDto.getComment() != null ) {
            productionEvaluation.setComment( productionEvaluationDto.getComment() );
        }
        productionEvaluation.setRelease( productionEvaluationDto.isRelease() );

        return productionEvaluation;
    }

    protected ProductMix productMixDtoToProductMix(ProductionEvaluationDto.ProductMixDto productMixDto) {
        if ( productMixDto == null ) {
            return null;
        }

        ProductMix productMix = new ProductMix();

        productMix.setId( productMixDto.getId() );

        return productMix;
    }

    protected ProductionEvaluationDto.ProductMixDto productMixToProductMixDto(ProductMix productMix) {
        if ( productMix == null ) {
            return null;
        }

        Long id = null;

        id = productMix.getId();

        String productName = null;

        ProductionEvaluationDto.ProductMixDto productMixDto = new ProductionEvaluationDto.ProductMixDto( id, productName );

        return productMixDto;
    }

    protected void productMixDtoToProductMix1(ProductionEvaluationDto.ProductMixDto productMixDto, ProductMix mappingTarget) {
        if ( productMixDto == null ) {
            return;
        }

        if ( productMixDto.getId() != null ) {
            mappingTarget.setId( productMixDto.getId() );
        }
    }
}
