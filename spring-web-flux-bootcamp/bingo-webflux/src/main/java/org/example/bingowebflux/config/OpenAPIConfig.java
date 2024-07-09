package org.example.bingowebflux.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
  @Bean
  public OpenAPI openAPI() {
    var info = new Info()
        .title("Bingo API")
        .description("Bingo API for WebFlux")
        .version("0.0.1");

    return new OpenAPI().info(info);
  }
}
