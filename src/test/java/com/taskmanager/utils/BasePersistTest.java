package com.taskmanager.utils;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest(properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
public abstract class BasePersistTest {

    @RegisterExtension
    protected static final PostgresContainer POSTGRES_CONTAINER = new PostgresContainer();

    @DynamicPropertySource
    public static void initProperties(DynamicPropertyRegistry registry) {
        POSTGRES_CONTAINER.initProperties(registry);
    }
}
