package com.justme8code.utterfresh_production_gathering_sys.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.ErrorObjectCreator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // or a custom status if you want

        System.out.println("JwtAuthenticationEntryPoint: " + authException.getMessage());
        var errorObject = ErrorObjectCreator.createErrorObject(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized access: " + authException.getMessage()
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorObject));
        response.getWriter().flush();
        response.flushBuffer();
    }
}
