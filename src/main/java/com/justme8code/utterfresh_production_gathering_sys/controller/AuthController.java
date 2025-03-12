package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.LoginRequest;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.LoginResponse;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.ResetPasswordRequest;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> requestLogin(
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            HttpServletResponse response,
            HttpServletRequest request) {

        LoginRequest userLogin = new LoginRequest(email, password);
        return ResponseEntity.ok(authService.login(userLogin, response, request));
    }

    @DeleteMapping()
    public ResponseEntity<Void> requestLogout(HttpServletResponse response){
        authService.logout(response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        System.out.println(resetPasswordRequest);
        authService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok("password reset successful");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        authService.changePassword(resetPasswordRequest);
        return ResponseEntity.ok("password changed successful");
    }
}