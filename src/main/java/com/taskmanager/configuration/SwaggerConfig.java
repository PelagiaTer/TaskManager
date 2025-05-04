package com.taskmanager.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.APIKEY, name = "Auth", paramName = "Auth",
        in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {
}
