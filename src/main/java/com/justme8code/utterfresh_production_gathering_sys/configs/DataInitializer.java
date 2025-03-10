package com.justme8code.utterfresh_production_gathering_sys.configs;

import com.justme8code.utterfresh_production_gathering_sys.models.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.repository.RoleRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final String ADMIN_EMAIL = "t@utterfresh.com"; // Default admin username

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void run(String... args) {
        // Initialize all roles if they don't exist
        List<Role> roles = new ArrayList<>();
        Arrays.stream(UserRole.values()).forEach(userRole -> {
            if (roleRepository.findRoleByUserRole(userRole) == null) {
                Role role = new Role();
                role.setUserRole(userRole);
                roles.add(role);
            }
        });
        if(!roles.isEmpty()) {
            roleRepository.saveAll(roles);
        }

        // Retrieve the ADMIN role
        Role adminRole = roleRepository.findRoleByUserRole(UserRole.ROLE_ADMIN);

        // Ensure an admin user exists
        Optional<User> existingAdmin = userRepository.findUserByEmail(ADMIN_EMAIL);

        if (existingAdmin.isPresent()) {
            User admin = existingAdmin.get();
            if (!admin.getRoles().contains(adminRole)) {
                admin.getRoles().add(adminRole);
                userRepository.save(admin);
            }
        } else {
            User admin = new User();
            admin.setEmail(ADMIN_EMAIL);
            admin.setRoles(new HashSet<>());
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
        }
    }
}
