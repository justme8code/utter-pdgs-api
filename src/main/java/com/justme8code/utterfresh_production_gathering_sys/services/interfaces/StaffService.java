package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.Staff;

public interface StaffService {
    void createStaff(Long userId);
    Staff getStaffById(Long id);
    void updateStaff(Staff staff);
    void deleteStaff(Long id);
}
