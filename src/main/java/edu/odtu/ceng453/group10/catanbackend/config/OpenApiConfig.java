package edu.odtu.ceng453.group10.catanbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the OpenAPI specification.
 * It defines beans for setting up the OpenAPI documentation for the REST API.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a custom OpenAPI bean to define the information section of the OpenAPI documentation.
     * This includes the title, version, and a description of the API.
     *
     * @return An instance of OpenAPI with the custom configuration applied.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Catan Backend API")
                        .version("v1")
                        .description("Documentation of Catan Backend API"));
    }
}
