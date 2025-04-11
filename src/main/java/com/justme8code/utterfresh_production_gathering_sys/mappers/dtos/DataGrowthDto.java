package com.justme8code.utterfresh_production_gathering_sys.mappers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DataGrowthDto {
    // Getters and Setters
    private LocalDate date;
    private long newProductions;
    private long newProductMixes;

}