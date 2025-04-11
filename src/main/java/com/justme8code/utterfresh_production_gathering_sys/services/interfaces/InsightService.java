package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.DataGrowthDto;

import java.time.LocalDate;
import java.util.List;

public interface InsightService {
    List<DataGrowthDto> getDataGrowth(LocalDate startDate, LocalDate endDate);
}