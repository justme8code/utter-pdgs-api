package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.UserMapper;
import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.UserDto2;
import com.justme8code.utterfresh_production_gathering_sys.models.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.repository.RoleRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequest;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, StaffRepository staffRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        // Encode password before saving
        User user = userMapper.toEntity(createUserRequest);


        // Fetch roles by their IDs to ensure they exist
        Set<Role> userRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleRepository.findById(role.getId()).ifPresent(userRoles::add);
        }


        // Set roles to the user
        user.setRoles(userRoles);

        // Save the user with assigned roles
        User userSaved = userRepository.save(user);

        Staff staff = new Staff();
        staff.setUser(userSaved);
        staff.setCompanyRole(user.getStaff().getCompanyRole());
        staff.setProfession(user.getStaff().getProfession());
        staffRepository.save(staff);
    }

    @Override
    public List<UserDto2> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto2).collect(Collectors.toList());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void updateUser(User user) {

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteUser(Long id) {

    }
}
