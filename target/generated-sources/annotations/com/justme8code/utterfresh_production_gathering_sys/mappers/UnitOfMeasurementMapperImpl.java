package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.UnitOfMeasurementDto;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurement;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T15:20:15+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UnitOfMeasurementMapperImpl implements UnitOfMeasurementMapper {

    @Override
    public UnitOfMeasurement toEntity(UnitOfMeasurementDto unitOfMeasurementDto) {
        if ( unitOfMeasurementDto == null ) {
            return null;
        }

        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();

        unitOfMeasurement.setId( unitOfMeasurementDto.getId() );
        unitOfMeasurement.setName( unitOfMeasurementDto.getName() );
        unitOfMeasurement.setAbbrev( unitOfMeasurementDto.getAbbrev() );

        return unitOfMeasurement;
    }

    @Override
    public UnitOfMeasurementDto toDto(UnitOfMeasurement unitOfMeasurement) {
        if ( unitOfMeasurement == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String abbrev = null;

        id = unitOfMeasurement.getId();
        name = unitOfMeasurement.getName();
        abbrev = unitOfMeasurement.getAbbrev();

        UnitOfMeasurementDto unitOfMeasurementDto = new UnitOfMeasurementDto( id, name, abbrev );

        return unitOfMeasurementDto;
    }

    @Override
    public UnitOfMeasurement partialUpdate(UnitOfMeasurementDto unitOfMeasurementDto, UnitOfMeasurement unitOfMeasurement) {
        if ( unitOfMeasurementDto == null ) {
            return unitOfMeasurement;
        }

        if ( unitOfMeasurementDto.getId() != null ) {
            unitOfMeasurement.setId( unitOfMeasurementDto.getId() );
        }
        if ( unitOfMeasurementDto.getName() != null ) {
            unitOfMeasurement.setName( unitOfMeasurementDto.getName() );
        }
        if ( unitOfMeasurementDto.getAbbrev() != null ) {
            unitOfMeasurement.setAbbrev( unitOfMeasurementDto.getAbbrev() );
        }

        return unitOfMeasurement;
    }
}
