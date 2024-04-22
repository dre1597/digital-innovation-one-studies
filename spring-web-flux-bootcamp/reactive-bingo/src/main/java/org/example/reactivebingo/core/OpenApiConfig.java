package org.example.reactivebingo.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Reactive Bingo", description = "API reativa de estudo de bingo"),
    servers = {
        @Server(url = "http://localhost:8080/reactive-bingo", description = "Local server"),
    }
)
public class OpenApiConfig {
}
