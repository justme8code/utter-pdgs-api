package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.mappers.UserMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.repository.RoleRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.StaffRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequestDto;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserResponseDto;
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
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, StaffRepository staffRepository, RoleRepository roleRepository,
                           UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
    @Transactional
    @Override
    public void createUser(CreateUserRequestDto createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setFullName(createUserRequest.getFullName());

        // ✅ Make sure to encode only the actual password
        user.setPwd(passwordEncoder.encode(createUserRequest.getPwd()));

        Set<Role> roles = createUserRequest.getRoles().stream().map(
                roleDto -> {
                    Role role = new Role();
                    role.setUserRole(roleDto.getUserRole());
                    role.setId(roleDto.getId());
                    return role;
                }
        ).collect(Collectors.toSet());

        user.setRoles(roles);

        User userSaved = userRepository.save(user);

        Staff staff = new Staff();
        staff.setUser(userSaved);
        staff.setCompanyRole(createUserRequest.getStaff().getCompanyRole()); // Use request data
        staff.setProfession(createUserRequest.getStaff().getProfession());  // Use request data
        staffRepository.save(staff);

        user.setStaff(staff);
        userRepository.save(userSaved);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto1).collect(Collectors.toList());
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
