package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.StaffService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;

    public StaffServiceImpl(StaffRepository staffRepository, UserRepository userRepository) {
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createStaff(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Staff staff = new Staff();
        staff.setUser(user);

        user.setStaff(staff);
        userRepository.save(user);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public void updateStaff(Staff staff) {

    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}
