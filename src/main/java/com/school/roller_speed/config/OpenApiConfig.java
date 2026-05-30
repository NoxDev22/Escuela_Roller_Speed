package com.school.roller_speed.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración principal de OpenAPI (Swagger) para la Escuela Roller Speed.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Escuela Roller Speed API")
                        .description("API REST para la gestión de la Escuela de Patinaje Roller Speed.\n" +
                                "Incluye gestión de estudiantes, profesores y usuarios del sistema.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Emmanuel")
                                .email("emmanuel@rollerspeed.com")
                                .url("https://rollerspeed.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}