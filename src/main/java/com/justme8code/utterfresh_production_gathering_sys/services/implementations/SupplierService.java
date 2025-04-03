package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.EntityException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.SupplierMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.SupplierDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Supplier;
import com.justme8code.utterfresh_production_gathering_sys.models.SupplierDtoPayload;
import com.justme8code.utterfresh_production_gathering_sys.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository,
                           SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public List<SupplierDto> createSuppliers(List<SupplierDtoPayload> suppliers){
        supplierRepository.saveAll(suppliers.stream().map(supplierMapper::toEntity).collect(Collectors.toList()));
        return getSuppliers();
    }
    public List<SupplierDto> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(supplierMapper::toDto).collect(Collectors.toList());
    }
    // Create a supplier
    public SupplierDto createSupplier(SupplierDtoPayload newSupplier) {
        return supplierMapper.toDto(supplierRepository
                .save(supplierMapper.toEntity(newSupplier)));
    }

    // Update a supplier
    public SupplierDto updateSupplier(long supplierId,SupplierDtoPayload updateSupplier) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new EntityException("Supplier not found", HttpStatus.NOT_FOUND));
        supplier.setFullName(updateSupplier.getFullName());
        supplier.setAddress(updateSupplier.getAddress());
        supplier.setEmailAddress(updateSupplier.getEmailAddress());
        supplier.setPhoneNumber(updateSupplier.getPhoneNumber());
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    public void deleteSupplier(long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
