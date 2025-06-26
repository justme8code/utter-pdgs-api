package com.justme8code.utterfresh_production_gathering_sys.dtos.productmix;

import com.justme8code.utterfresh_production_gathering_sys.models.event.ProductMix;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductMix}
 */
@Value
public class PMOutputLessDetail implements Serializable {
    Long id;
    String  productName;
}