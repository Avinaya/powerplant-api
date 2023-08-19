package com.proshore.powerplant.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    // Define constants for API version and module name
    private final static String API_VERSION = "1.0";
    private final static String MODULE_NAME = "Power Plant";

    /**
     * Custom OpenAPI bean configuration.
     *
     * @return An instance of OpenAPI configured with API information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // Create and configure the OpenAPI instance
        return new OpenAPI()
                .components(new Components()) // Define components (e.g., security schemes)
                .info(new Info().title(MODULE_NAME).version(API_VERSION)); // Set API title and version
    }
}
