package com.product.managementsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApi30Config {

    @Bean
    public OpenAPI springOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Product Management System API documentations").version("v1").description("Welcome to the API documentation for Product Management System, For queries, contact the dev. Thank you."));
        return openAPI;
    }
}
