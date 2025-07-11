package com.justme8code.utterfresh_production_gathering_sys.dtos.inventory;

import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link RawMaterial}
 */
@Value
@Getter
@Setter
public class RawMaterialDto implements Serializable {
    Long id;
    String name;
    String uom;
    List<IngredientDto> ingredients;
}