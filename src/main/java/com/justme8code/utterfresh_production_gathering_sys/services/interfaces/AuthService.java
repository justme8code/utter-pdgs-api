package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.models.UserRole;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.LoginRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ResetPasswordRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void addUser(User user, UserRole userRole);

    LoginResponse login(LoginRequest userLogin, HttpServletResponse response, HttpServletRequest request);

    void logout(HttpServletResponse response);

    void createNewUser(User user);

    void resetPassword(ResetPasswordRequest resetPasswordRequest);

    void changePassword(ResetPasswordRequest resetPasswordRequest);
}