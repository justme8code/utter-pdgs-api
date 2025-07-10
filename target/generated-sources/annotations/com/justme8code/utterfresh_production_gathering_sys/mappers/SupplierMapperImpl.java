package com.justme8code.utterfresh_production_gathering_sys.mappers;

import com.justme8code.utterfresh_production_gathering_sys.dtos.users.SupplierDto;
import com.justme8code.utterfresh_production_gathering_sys.models.users.Supplier;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-07T10:25:26+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier toEntity(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( supplierDto.getId() );
        supplier.setFullName( supplierDto.getFullName() );
        supplier.setAddress( supplierDto.getAddress() );
        supplier.setPhoneNumber( supplierDto.getPhoneNumber() );
        supplier.setEmailAddress( supplierDto.getEmailAddress() );

        return supplier;
    }

    @Override
    public SupplierDto toDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;
        String phoneNumber = null;
        String emailAddress = null;
        String address = null;

        id = supplier.getId();
        fullName = supplier.getFullName();
        phoneNumber = supplier.getPhoneNumber();
        emailAddress = supplier.getEmailAddress();
        address = supplier.getAddress();

        SupplierDto supplierDto = new SupplierDto( id, fullName, phoneNumber, emailAddress, address );

        return supplierDto;
    }

    @Override
    public Supplier partialUpdate(SupplierDto supplierDto, Supplier supplier) {
        if ( supplierDto == null ) {
            return supplier;
        }

        if ( supplierDto.getId() != null ) {
            supplier.setId( supplierDto.getId() );
        }
        if ( supplierDto.getFullName() != null ) {
            supplier.setFullName( supplierDto.getFullName() );
        }
        if ( supplierDto.getAddress() != null ) {
            supplier.setAddress( supplierDto.getAddress() );
        }
        if ( supplierDto.getPhoneNumber() != null ) {
            supplier.setPhoneNumber( supplierDto.getPhoneNumber() );
        }
        if ( supplierDto.getEmailAddress() != null ) {
            supplier.setEmailAddress( supplierDto.getEmailAddress() );
        }

        return supplier;
    }
}
