package sample.challenges;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

class Question2Test {
  private final Faker faker = new Faker(new Locale("pt", "BR"));
  private Question2 q2;

  @BeforeEach
  void setup() {
    q2 = new Question2();
  }

  @AfterEach
  void tearDown() {
    q2 = null;
  }

  @Test
  void countAdminsTestNoAdmin() {
    var users = generateUsers((long) faker.number().randomDigitNotZero(), false);

    StepVerifier.create(q2.countAdmins(users))
        .expectNext(0L)
        .verifyComplete();
  }

  @Test
  void countAdminsTestAllAdmin() {
    var users = generateUsers((long) faker.number().randomDigitNotZero(), true);

    StepVerifier.create(q2.countAdmins(users))
        .expectNext((long) users.size())
        .verifyComplete();
  }

  @Test
  void countAdminsTestRandomAdmins() {
    var users = generateUsers((long) faker.number().randomDigitNotZero(), true);
    var admins = users.stream().filter(User::isAdmin).toList();

    StepVerifier.create(q2.countAdmins(users))
        .expectNext((long) admins.size())
        .verifyComplete();
  }

  @Test
  void countAdminsTestEmptyList() {
    List<User> users = Collections.emptyList();

    StepVerifier.create(q2.countAdmins(users))
        .expectNext(0L)
        .verifyComplete();
  }

  @Test
  void countAdminsTestSingleUserAdmin() {
    List<User> users = generateUsers(1L, true);

    StepVerifier.create(q2.countAdmins(users))
        .expectNext(1L)
        .verifyComplete();
  }

  @Test
  void countAdminsTestSingleUserNonAdmin() {
    List<User> users = generateUsers(1L, false);

    StepVerifier.create(q2.countAdmins(users))
        .expectNext(0L)
        .verifyComplete();
  }

  private List<User> generateUsers(final Long limit, final boolean isAdmin) {
    return Stream.generate(() ->
        new User(
            faker.random().nextLong(),
            faker.name().username(),
            faker.internet().emailAddress(),
            UUID.randomUUID().toString(),
            isAdmin))
        .limit(limit)
        .toList();
  }
}
