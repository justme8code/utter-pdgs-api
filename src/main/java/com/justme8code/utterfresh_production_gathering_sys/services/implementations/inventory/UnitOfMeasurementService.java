package com.justme8code.utterfresh_production_gathering_sys.services.implementations.inventory;

import com.justme8code.utterfresh_production_gathering_sys.dtos.inventory.UnitOfMeasurementDto;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.UnitOfMeasurementMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurement;
import com.justme8code.utterfresh_production_gathering_sys.models.inventory.UnitOfMeasurementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitOfMeasurementService {
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;
    private final UnitOfMeasurementMapper unitOfMeasurementMapper;

    public UnitOfMeasurementService(UnitOfMeasurementRepository unitOfMeasurementRepository,
                                    UnitOfMeasurementMapper unitOfMeasurementMapper) {
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
        this.unitOfMeasurementMapper = unitOfMeasurementMapper;
    }

    //create uom
    public UnitOfMeasurementDto createUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        return unitOfMeasurementMapper.toDto(unitOfMeasurementRepository.save(unitOfMeasurement));
    }

    public List<UnitOfMeasurementDto> getAllUnitOfMeasurements() {
        return unitOfMeasurementRepository.findAll().stream().map(unitOfMeasurementMapper::toDto).collect(Collectors.toList());
    }

    public UnitOfMeasurementDto getUnitOfMeasurementById(Long id) {
        return unitOfMeasurementMapper.toDto(unitOfMeasurementRepository.findById(id)
                .orElseThrow(() -> new EntityException("Uom Not found", HttpStatus.NOT_FOUND)));
    }

    public UnitOfMeasurementDto updateUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        UnitOfMeasurement updatedUnitOfMeasurement = unitOfMeasurementRepository.findById(unitOfMeasurement.getId())
                .orElseThrow(() -> new EntityException("Uom Not found", HttpStatus.NOT_FOUND));
        updatedUnitOfMeasurement.setName(unitOfMeasurement.getName());
        updatedUnitOfMeasurement.setAbbrev(unitOfMeasurement.getAbbrev());
        return unitOfMeasurementMapper.toDto(unitOfMeasurementRepository.save(updatedUnitOfMeasurement));
    }

    public void deleteUnitOfMeasurementById(Long id) {
        unitOfMeasurementRepository.deleteById(id);
    }
}
