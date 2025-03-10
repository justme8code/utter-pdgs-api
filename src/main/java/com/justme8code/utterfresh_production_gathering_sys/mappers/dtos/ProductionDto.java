package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import com.justme8code.utterfresh_production_gathering_sys.models.Production;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.justme8code.utterfresh_production_gathering_sys.models.Production}
 */
public record ProductionDto(LocalDateTime createdAt, Long id, String productionNumber, String name, LocalDate startDate,
                            LocalDate endDate, StaffDto staff,
                            Production.ProductionStatus status) implements Serializable {
}