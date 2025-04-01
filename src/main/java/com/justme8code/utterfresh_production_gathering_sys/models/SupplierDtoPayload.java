package com.justme8code.utterfresh_production_gathering_sys.models;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Supplier}
 */
@Value
public class SupplierDtoPayload implements Serializable {
    String fullName;
    String address;
    String phoneNumber;
    String emailAddress;
}