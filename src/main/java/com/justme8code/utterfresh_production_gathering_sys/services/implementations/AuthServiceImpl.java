package com.justme8code.utterfresh_production_gathering_sys.services.implementations;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.UnExpectedException;
import com.justme8code.utterfresh_production_gathering_sys.mappers.UserMapper;
import com.justme8code.utterfresh_production_gathering_sys.models.Role;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import com.justme8code.utterfresh_production_gathering_sys.repository.RoleRepository;
import com.justme8code.utterfresh_production_gathering_sys.repository.UserRepository;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.LoginRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ResetPasswordRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.LoginResponse;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.AuthService;
import com.justme8code.utterfresh_production_gathering_sys.utils.JwtAuthorizer;
import com.justme8code.utterfresh_production_gathering_sys.utils.RequestResponseUtil;
import com.justme8code.utterfresh_production_gathering_sys.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtAuthorizer authorizer;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtAuthorizer authorizer, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.authorizer = authorizer;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Deprecated
    public void addUser(User user, UserRole userRole) {
        // Assign default role (USER)
        assignRoleToUser(user, userRole);
        saveUser(user);
    }

    @Override
    public LoginResponse login(LoginRequest userLogin, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(userLogin.getEmail());
        System.out.println(userLogin.getPassword());
        User user = new User();
        user.setEmail(userLogin.getEmail());
        user.setPwd(userLogin.getPassword());
        Authentication authentication = SecurityUtils.authenticateUser(user, authenticationManager);
        String token = authorizer.generateToken(authentication);
        RequestResponseUtil.addAuthCookieToResponse(response, token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(token);

        userRepository.findUserByEmail(user.getEmail())
                .ifPresent(user1 -> loginResponse.setUser(userMapper.toDto(user1)));

        return loginResponse;
    }

    @Override
    public void logout(HttpServletResponse response) {
        RequestResponseUtil.removeAuthCookieFromResponse(response);
    }

    @Override
    public void createNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = retreiveUser(resetPasswordRequest);
        if (user.getPwd() == null) {  // Only reset if password is NULL
            user.setPwd(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Password reset successful");
        }
    }

    @Override
    public void changePassword(ResetPasswordRequest resetPasswordRequest) {
        User user = retreiveUser(resetPasswordRequest);

        if (user.getPwd() != null && passwordEncoder.matches(resetPasswordRequest.getPassword(), user.getPwd())) {
            user.setPwd(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Password reset not successful");
        }
    }

    private User retreiveUser(ResetPasswordRequest resetPasswordRequest) {
        return userRepository.findUserByEmail(resetPasswordRequest.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Deprecated
    public void assignRoleToUser(User user, UserRole userRole) {
        verifyAdminRole();

        Optional<User> targetUser = userRepository.findUserByEmail(user.getEmail());
        if (targetUser.isPresent()) {
            throw new UnExpectedException("User already exists");
        }
        Role role = roleRepository.findRoleByUserRole(userRole);
        if (role != null) {
            user.setRoles(new HashSet<>());
            user.getRoles().add(role);
        }
        throw new UnExpectedException("Could not assign role to user");
    }

    private void saveUser(User user) {
        // Assign the role to the user
        userRepository.save(user);
    }

    private void verifyAdminRole() {
        // Get the currently logged-in user ID
        String currentUserId = SecurityUtils.getCurrentUserId();
        // Fetch the admin user
        User admin = userRepository.findUserByEmail(currentUserId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Ensure the logged-in user is an admin
        boolean isAdmin = admin.getRoles().stream()
                .anyMatch(role -> role.getUserRole().equals(UserRole.ROLE_ADMIN));
        if (!isAdmin) {
            throw new UnExpectedException("Not authorized to assign roles");
        }
    }

}