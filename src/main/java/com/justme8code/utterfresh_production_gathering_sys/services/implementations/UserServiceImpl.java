package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    public UserServiceImpl(UserRepository userRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void createUser(User user) {
        User u = userRepository.save(user);
        userRepository.save(u);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
