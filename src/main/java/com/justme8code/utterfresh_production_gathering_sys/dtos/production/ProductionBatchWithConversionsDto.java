package com.justme8code.utterfresh_production_gathering_sys.dtos.production;

import com.justme8code.utterfresh_production_gathering_sys.dtos.conversion.ConversionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionBatchWithConversionsDto {
    private Long id;
    private String name;
    private boolean active;
    private List<ConversionDto> conversions;
}
