package com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests;

import com.justme8code.utterfresh_production_gathering_sys.models.inventory.RawMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialsPayload {
    private List<RawMaterial> rawMaterials;
}
