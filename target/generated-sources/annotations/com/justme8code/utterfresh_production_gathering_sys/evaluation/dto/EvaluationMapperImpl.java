package com.justme8code.utterfresh_production_gathering_sys.evaluation.dto;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.Evaluation;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.EvaluationType;
import com.justme8code.utterfresh_production_gathering_sys.evaluation.ProductionEvaluation;
import com.justme8code.utterfresh_production_gathering_sys.mappers.StaffMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.event.Production;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Staff;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class EvaluationMapperImpl implements EvaluationMapper {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Evaluation toEntity(EvaluationDto evaluationDto) {
        if ( evaluationDto == null ) {
            return null;
        }

        Evaluation evaluation = new Evaluation();

        evaluation.setCreatedAt( evaluationDto.getCreatedAt() );
        evaluation.setId( evaluationDto.getId() );
        evaluation.setBatchRange( evaluationDto.getBatchRange() );
        evaluation.setStaff( staffMapper.toEntity( evaluationDto.getStaff() ) );
        evaluation.setManufacturedDate( evaluationDto.getManufacturedDate() );
        evaluation.setExpirationDate( evaluationDto.getExpirationDate() );
        evaluation.setEvaluationType( evaluationDto.getEvaluationType() );

        linkProductEvaluations( evaluation );

        return evaluation;
    }

    @Override
    public EvaluationDto toDto(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        Long id = null;
        LocalDateTime createdAt = null;
        String batchRange = null;
        StaffDto staff = null;
        LocalDate manufacturedDate = null;
        LocalDate expirationDate = null;
        EvaluationType evaluationType = null;

        id = evaluation.getId();
        createdAt = evaluation.getCreatedAt();
        batchRange = evaluation.getBatchRange();
        staff = staffMapper.toDto( evaluation.getStaff() );
        manufacturedDate = evaluation.getManufacturedDate();
        expirationDate = evaluation.getExpirationDate();
        evaluationType = evaluation.getEvaluationType();

        EvaluationDto evaluationDto = new EvaluationDto( id, createdAt, batchRange, staff, manufacturedDate, expirationDate, evaluationType );

        return evaluationDto;
    }

    @Override
    public Evaluation partialUpdate(EvaluationDto evaluationDto, Evaluation evaluation) {
        if ( evaluationDto == null ) {
            return evaluation;
        }

        if ( evaluationDto.getCreatedAt() != null ) {
            evaluation.setCreatedAt( evaluationDto.getCreatedAt() );
        }
        if ( evaluationDto.getId() != null ) {
            evaluation.setId( evaluationDto.getId() );
        }
        if ( evaluationDto.getBatchRange() != null ) {
            evaluation.setBatchRange( evaluationDto.getBatchRange() );
        }
        if ( evaluationDto.getStaff() != null ) {
            if ( evaluation.getStaff() == null ) {
                evaluation.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( evaluationDto.getStaff(), evaluation.getStaff() );
        }
        if ( evaluationDto.getManufacturedDate() != null ) {
            evaluation.setManufacturedDate( evaluationDto.getManufacturedDate() );
        }
        if ( evaluationDto.getExpirationDate() != null ) {
            evaluation.setExpirationDate( evaluationDto.getExpirationDate() );
        }
        if ( evaluationDto.getEvaluationType() != null ) {
            evaluation.setEvaluationType( evaluationDto.getEvaluationType() );
        }

        linkProductEvaluations( evaluation );

        return evaluation;
    }

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

        linkProductEvaluations( evaluation );

        return evaluation;
    }

    @Override
    public Evaluation toEntity(EvaluationInfoDto evaluationInfoDto) {
        if ( evaluationInfoDto == null ) {
            return null;
        }

        Evaluation evaluation = new Evaluation();

        evaluation.setProduction( evaluationInfoDtoToProduction( evaluationInfoDto ) );
        evaluation.setCreatedAt( evaluationInfoDto.getCreatedAt() );
        evaluation.setId( evaluationInfoDto.getId() );
        evaluation.setBatchRange( evaluationInfoDto.getBatchRange() );
        evaluation.setStaff( staffMapper.toEntity( evaluationInfoDto.getStaff() ) );
        evaluation.setManufacturedDate( evaluationInfoDto.getManufacturedDate() );
        evaluation.setExpirationDate( evaluationInfoDto.getExpirationDate() );
        evaluation.setEvaluationType( evaluationInfoDto.getEvaluationType() );

        linkProductEvaluations( evaluation );

        return evaluation;
    }

    @Override
    public EvaluationInfoDto toDto2(Evaluation evaluation) {
        if ( evaluation == null ) {
            return null;
        }

        Long productionId = null;
        LocalDateTime createdAt = null;
        Long id = null;
        String batchRange = null;
        StaffDto staff = null;
        LocalDate manufacturedDate = null;
        EvaluationType evaluationType = null;
        LocalDate expirationDate = null;

        productionId = evaluationProductionId( evaluation );
        createdAt = evaluation.getCreatedAt();
        id = evaluation.getId();
        batchRange = evaluation.getBatchRange();
        staff = staffMapper.toDto( evaluation.getStaff() );
        manufacturedDate = evaluation.getManufacturedDate();
        evaluationType = evaluation.getEvaluationType();
        expirationDate = evaluation.getExpirationDate();

        List<ProductionEvaluationDto> productEvaluations = null;

        EvaluationInfoDto evaluationInfoDto = new EvaluationInfoDto( createdAt, id, productionId, batchRange, staff, manufacturedDate, evaluationType, expirationDate, productEvaluations );

        return evaluationInfoDto;
    }

    @Override
    public Evaluation partialUpdate(EvaluationInfoDto evaluationInfoDto, Evaluation evaluation) {
        if ( evaluationInfoDto == null ) {
            return evaluation;
        }

        if ( evaluation.getProduction() == null ) {
            evaluation.setProduction( new Production() );
        }
        evaluationInfoDtoToProduction1( evaluationInfoDto, evaluation.getProduction() );
        if ( evaluationInfoDto.getCreatedAt() != null ) {
            evaluation.setCreatedAt( evaluationInfoDto.getCreatedAt() );
        }
        if ( evaluationInfoDto.getId() != null ) {
            evaluation.setId( evaluationInfoDto.getId() );
        }
        if ( evaluationInfoDto.getBatchRange() != null ) {
            evaluation.setBatchRange( evaluationInfoDto.getBatchRange() );
        }
        if ( evaluationInfoDto.getStaff() != null ) {
            if ( evaluation.getStaff() == null ) {
                evaluation.setStaff( new Staff() );
            }
            staffMapper.partialUpdate( evaluationInfoDto.getStaff(), evaluation.getStaff() );
        }
        if ( evaluationInfoDto.getManufacturedDate() != null ) {
            evaluation.setManufacturedDate( evaluationInfoDto.getManufacturedDate() );
        }
        if ( evaluationInfoDto.getExpirationDate() != null ) {
            evaluation.setExpirationDate( evaluationInfoDto.getExpirationDate() );
        }
        if ( evaluationInfoDto.getEvaluationType() != null ) {
            evaluation.setEvaluationType( evaluationInfoDto.getEvaluationType() );
        }

        linkProductEvaluations( evaluation );

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

    protected Production evaluationInfoDtoToProduction(EvaluationInfoDto evaluationInfoDto) {
        if ( evaluationInfoDto == null ) {
            return null;
        }

        Production production = new Production();

        production.setId( evaluationInfoDto.getProductionId() );

        return production;
    }

    private Long evaluationProductionId(Evaluation evaluation) {
        Production production = evaluation.getProduction();
        if ( production == null ) {
            return null;
        }
        return production.getId();
    }

    protected void evaluationInfoDtoToProduction1(EvaluationInfoDto evaluationInfoDto, Production mappingTarget) {
        if ( evaluationInfoDto == null ) {
            return;
        }

        if ( evaluationInfoDto.getProductionId() != null ) {
            mappingTarget.setId( evaluationInfoDto.getProductionId() );
        }
    }
}
