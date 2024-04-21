package org.example.reactiveflashcards;

import org.example.reactiveflashcards.core.RetryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@ConfigurationPropertiesScan(basePackageClasses = RetryConfig.class)
@SpringBootApplication
@EnableReactiveMongoAuditing(dateTimeProviderRef = "dateTimeProvider")
public class ReactiveFlashcardsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveFlashcardsApplication.class, args);
  }

}
