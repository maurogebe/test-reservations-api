package com.example.demo.application.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        
        return new OpenAPI()
            .info(new Info()
                .title("Reservations API")
                .description("API for reservations of hotel")
                .version("1.0")
                .contact((new Contact()
                    .name("Mauricio Guerra")
                    .url("https://github.com/maurogebe")
                    .email("maurogebe@gmail.com")
                ))
            )
            .components(new Components()
                .addResponses("Unauthorized", new ApiResponse().description("Unauthorized access"))
            )
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                )
            );
    }
}
