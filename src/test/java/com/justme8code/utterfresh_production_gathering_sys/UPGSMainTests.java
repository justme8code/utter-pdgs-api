package com.justme8code.utterfresh_production_gathering_sys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers // This annotation activates Testcontainers support for JUnit 5
class UPGSMainTests {

    // This creates and manages a PostgreSQL Docker container.
    // The `static` keyword means the container is started only ONCE for all tests in this class.
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    // This method dynamically provides the database connection properties to Spring Boot.
    // It runs BEFORE the application context is started.
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        // You can also add other properties here if needed for tests
        // e.g., registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Test
    void contextLoads() {
        // This test will now run successfully!
        // It's connected to a real, but temporary, PostgreSQL database.
        System.out.println("✅ Context loaded successfully with Testcontainers!");
    }
}