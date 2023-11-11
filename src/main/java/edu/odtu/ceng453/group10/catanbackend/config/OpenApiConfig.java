package edu.odtu.ceng453.group10.catanbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080").description("Development server"))
                .info(new Info()
                        .title("Backend API for")
                        .version("v1.0")
                        .description("This is the API documentation for the Backend."));
    }
}
