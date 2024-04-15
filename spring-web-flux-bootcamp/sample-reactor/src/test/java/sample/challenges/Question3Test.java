package sample.challenges;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.Locale;

class Question3Test {
  private final Faker faker = new Faker(new Locale("pt", "BR"));

  private Question3 q3;

  @BeforeEach
  void setup() {
    q3 = new Question3();
  }

  @AfterEach
  void tearDown() {
    q3 = null;
  }

  @Test
  void validUser() {
    StepVerifier.create(q3.userIsValid(new User(1L, "admin1", "admin1@email.com", "any_password1", true)))
        .expectNextCount(0)
        .verifyComplete();
  }

  @Test
  void invalidUser() {
    StepVerifier.create(q3.userIsValid(new User(1L, "admin1", "admin1@email.com", "invalid", false)))
        .expectNextCount(0)
        .verifyError();
  }
}
