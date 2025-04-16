package com.hr.hrsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig(){

        return new OpenAPI()
                .info(
                        new Info().title("HR System")

                )
                .servers(List.of(new Server().url("http://localhost:8080").description("local"),
                        new Server().url("http://localhost:8080").description("live")))
                .tags(List.of(
                        new Tag().name("User APIs"),
                        new Tag().name("Check-in API"),
                        new Tag().name("Check-out API"),
                        new Tag().name("File APIs")

                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes( // JWT token
                        "bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")

                ));



    }





}
