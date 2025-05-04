package com.taskmanager.utils;

import org.junit.jupiter.api.extension.Extension;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer implements Extension {

    private static final String IMAGE_VERSION = "postgres:15.5-alpine";
    private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>(IMAGE_VERSION);

    static {
        CONTAINER.start();
    }

    public void initProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }
}
