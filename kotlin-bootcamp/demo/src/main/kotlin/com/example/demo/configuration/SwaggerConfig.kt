package com.example.demo.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/api/customers/**", "/api/credits/**")
            .displayName("Credit Application System")
            .build()
    }
}
