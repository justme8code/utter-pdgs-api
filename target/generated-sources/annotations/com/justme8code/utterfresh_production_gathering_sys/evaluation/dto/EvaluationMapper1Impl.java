package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Taste;
import com.justme8code.utterfresh_production_gathering_sys.mappers.StaffMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T10:25:27+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EvaluationMapper1Impl implements EvaluationMapper1 {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Evaluation toEntity(EvaluationPayload evaluationPayload) {
        if ( evaluationPayload == null ) {
            return null;
        }

        Evaluation evaluation = new Evaluation();

        evaluation.setId( evaluationPayload.getId() );
        evaluation.setBatchRange( evaluationPayload.getBatchRange() );
        evaluation.setStaff( staffMapper.toEntity( evaluationPayload.getStaff() ) );
        evaluation.setManufacturedDate( evaluationPayload.getManufacturedDate() );
        evaluation.setExpirationDate( evaluationPayload.getExpirationDate() );
        evaluation.setEvaluationType( evaluationPayload.getEvaluationType() );
        evaluation.setProductionEvaluations( productionEvaluationDto1ListToProductionEvaluationList( evaluationPayload.getProductionEvaluations() ) );

        linkProductionEvaluations( evaluation );

        return evaluation;
    }

    @Override
    public EvaluationPayload toDto(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        List<EvaluationPayload.ProductionEvaluationDto1> productionEvaluations = null;
        Long id = null;
        String batchRange = null;
        StaffDto staff = null;
        LocalDate manufacturedDate = null;
        LocalDate expirationDate = null;
        EvaluationType evaluationType = null;

        productionEvaluations = productionEvaluationListToProductionEvaluationDto1List( evaluation.getProductionEvaluations() );
        id = evaluation.getId();
        batchRange = evaluation.getBatchRange();
        staff = staffMapper.toDto( evaluation.getStaff() );
        manufacturedDate = evaluation.getManufacturedDate();
        expirationDate = evaluation.getExpirationDate();
        evaluationType = evaluation.getEvaluationType();

        EvaluationPayload evaluationPayload = new EvaluationPayload( id, batchRange, staff, manufacturedDate, expirationDate, evaluationType, productionEvaluations );

        return evaluationPayload;
    }

    @Override
    public Evaluation partialUpdate(EvaluationPayload evaluationPayload, Evaluation evaluation) {
        if ( evaluationPayload == null ) {
            return evaluation;
        }

        if ( evaluationPayload.getId() != null ) {
            evaluation.setId( evaluationPayload.getId() );
        }
        if ( evaluationPayload.getBatchRange() != null ) {
            evaluation.setBatchRange( evaluationPayload.getBatchRange() );
        }
        if ( evaluationPayload.getStaff() != null ) {
            if ( evaluation.getStaff() == null ) {
                evaluation.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( evaluationPayload.getStaff(), evaluation.getStaff() );
        }
        if ( evaluationPayload.getManufacturedDate() != null ) {
            evaluation.setManufacturedDate( evaluationPayload.getManufacturedDate() );
        }
        if ( evaluationPayload.getExpirationDate() != null ) {
            evaluation.setExpirationDate( evaluationPayload.getExpirationDate() );
        }
        if ( evaluationPayload.getEvaluationType() != null ) {
            evaluation.setEvaluationType( evaluationPayload.getEvaluationType() );
        }
        if ( evaluation.getProductionEvaluations() != null ) {
            List<ProductionEvaluation> list = productionEvaluationDto1ListToProductionEvaluationList( evaluationPayload.getProductionEvaluations() );
            if ( list != null ) {
                evaluation.getProductionEvaluations().clear();
                evaluation.getProductionEvaluations().addAll( list );
            }
        }
        else {
            List<ProductionEvaluation> list = productionEvaluationDto1ListToProductionEvaluationList( evaluationPayload.getProductionEvaluations() );
            if ( list != null ) {
                evaluation.setProductionEvaluations( list );
            }
        }

        linkProductionEvaluations( evaluation );

        return evaluation;
    }

    protected ProductionEvaluation productionEvaluationDto1ToProductionEvaluation(EvaluationPayload.ProductionEvaluationDto1 productionEvaluationDto1) {
        if ( productionEvaluationDto1 == null ) {
            return null;
        }

        ProductionEvaluation productionEvaluation = new ProductionEvaluation();

        productionEvaluation.setId( productionEvaluationDto1.getId() );
        productionEvaluation.setTaste( productionEvaluationDto1.getTaste() );
        productionEvaluation.setAfterTaste( productionEvaluationDto1.getAfterTaste() );
        productionEvaluation.setViscosity( productionEvaluationDto1.getViscosity() );
        productionEvaluation.setComment( productionEvaluationDto1.getComment() );
        productionEvaluation.setRelease( productionEvaluationDto1.isRelease() );

        return productionEvaluation;
    }

    protected List<ProductionEvaluation> productionEvaluationDto1ListToProductionEvaluationList(List<EvaluationPayload.ProductionEvaluationDto1> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductionEvaluation> list1 = new ArrayList<ProductionEvaluation>( list.size() );
        for ( EvaluationPayload.ProductionEvaluationDto1 productionEvaluationDto1 : list ) {
            list1.add( productionEvaluationDto1ToProductionEvaluation( productionEvaluationDto1 ) );
        }

        return list1;
    }

    protected EvaluationPayload.ProductionEvaluationDto1 productionEvaluationToProductionEvaluationDto1(ProductionEvaluation productionEvaluation) {
        if ( productionEvaluation == null ) {
            return null;
        }

        Long id = null;
        Taste taste = null;
        Taste afterTaste = null;
        Taste viscosity = null;
        String comment = null;
        boolean release = false;

        id = productionEvaluation.getId();
        taste = productionEvaluation.getTaste();
        afterTaste = productionEvaluation.getAfterTaste();
        viscosity = productionEvaluation.getViscosity();
        comment = productionEvaluation.getComment();
        release = productionEvaluation.isRelease();

        Long productMixId = null;

        EvaluationPayload.ProductionEvaluationDto1 productionEvaluationDto1 = new EvaluationPayload.ProductionEvaluationDto1( id, productMixId, taste, afterTaste, viscosity, comment, release );

        return productionEvaluationDto1;
    }

    protected List<EvaluationPayload.ProductionEvaluationDto1> productionEvaluationListToProductionEvaluationDto1List(List<ProductionEvaluation> list) {
        if ( list == null ) {
            return null;
        }

        List<EvaluationPayload.ProductionEvaluationDto1> list1 = new ArrayList<EvaluationPayload.ProductionEvaluationDto1>( list.size() );
        for ( ProductionEvaluation productionEvaluation : list ) {
            list1.add( productionEvaluationToProductionEvaluationDto1( productionEvaluation ) );
        }

        return list1;
    }
}
