package com.ecommerce.ecommerce_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Simple Ecommerce API")
                        .description("Simple API for an ecommerce platform")
                        .version("v1.0")
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")))
                        .externalDocs(new ExternalDocumentation()
                            .description("My GitHub")
                            .url("https://github.com/YacoCappelletti"));
    }
}
