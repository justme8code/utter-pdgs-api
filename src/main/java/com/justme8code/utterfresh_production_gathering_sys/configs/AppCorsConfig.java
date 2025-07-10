package com.justme8code.utterfresh_production_gathering_sys.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class AppCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow credentials (cookies, tokens)
        config.setAllowedOrigins(List.of("http://localhost:3000", "https://utter-pdgs-web.vercel.app")); // Allowed origins
        config.setAllowedHeaders(List.of("*")); // Allow all headers
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // HTTP Methods allowed

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply CORS configuration to all paths

        return new CorsFilter(source);
    }
}