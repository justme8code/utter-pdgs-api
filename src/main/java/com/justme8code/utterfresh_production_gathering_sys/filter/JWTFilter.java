package com.justme8code.utterfresh_production_gathering_sys.filter;

import com.justme8code.utterfresh_production_gathering_sys.exceptions.JWTAuthenticationException;
import com.justme8code.utterfresh_production_gathering_sys.exceptions.JWTException;
import com.justme8code.utterfresh_production_gathering_sys.services.implementations.users.CustomUserDetailsService;
import com.justme8code.utterfresh_production_gathering_sys.utils.JwtAuthorizer;
import com.justme8code.utterfresh_production_gathering_sys.utils.JwtFilterUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final Logger JwtFilterLogger = LoggerFactory.getLogger(JWTFilter.class);
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthorizer authorizer;


    public JWTFilter(CustomUserDetailsService userDetailsService, JwtAuthorizer authorizer) {
        this.customUserDetailsService = userDetailsService;
        this.authorizer = authorizer;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtFilterLogger.debug("JWTFilter doFilter called : {}", request.getRequestURI());

        if (request.getRequestURI().contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = JwtFilterUtil.tryGetJwtTokenFromAuthorizationHeaderOrCookieListsRequest(request);
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            try {
                Claims claims = authorizer.validateToken(jwtToken);
                JwtFilterUtil.isTokenExpired(claims);
                setSecurityContextAfterJwtTokenAuthentication(claims, request);
            } catch (JWTException | UsernameNotFoundException e) {
                throw new JWTAuthenticationException(e.getMessage(), e);
            }
        }


        filterChain.doFilter(request, response);

    }


    private void setSecurityContextAfterJwtTokenAuthentication(Claims claims, HttpServletRequest request) {
        String userId = claims.getSubject();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}