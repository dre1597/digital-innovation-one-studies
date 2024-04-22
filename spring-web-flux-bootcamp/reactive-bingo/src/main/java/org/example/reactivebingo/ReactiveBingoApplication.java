package org.example.reactivebingo;

import org.example.reactivebingo.core.RetryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@ConfigurationPropertiesScan(basePackageClasses = RetryConfig.class)
@SpringBootApplication
@EnableReactiveMongoAuditing(dateTimeProviderRef = "dateTimeProvider")
public class ReactiveBingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveBingoApplication.class, args);
	}
}
