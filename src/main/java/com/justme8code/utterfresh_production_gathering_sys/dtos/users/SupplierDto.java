package com.justme8code.utterfresh_production_gathering_sys.dtos.users;

import com.justme8code.utterfresh_production_gathering_sys.models.users.Supplier;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Supplier}
 */
@Value
public class SupplierDto implements Serializable {
    Long id;
    String fullName;
    String phoneNumber;
    String emailAddress;
    String address;
}