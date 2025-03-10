package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;

import java.util.List;

public interface StaffService {
    void createStaff(Long userId,Staff staff);
    Staff getStaffById(Long id);
    void updateStaff(Staff staff);
    void deleteStaff(Long id);
    List<StaffDto> getAllStaffs();

}
