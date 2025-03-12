package com.justme8code.utterfresh_production_gathering_sys.utils;

import com.justme8code.utterfresh_production_gathering_sys.models.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    private SecurityUtils() {}
    // Method to fetch the current user's ID
    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername(); // Assuming username is the user ID
            }
        }
        throw new AccessDeniedException("You are not authenticated");
    }


    public static  Authentication authenticateUser(User user, AuthenticationManager authenticationManager) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPwd()));
    }


}
