package sample.challenges;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Question1Test {
  private final Faker faker = new Faker(new Locale("pt", "BR"));
  private Question1 q1;

  @BeforeEach
  void setup() {
    q1 = new Question1();
  }

  @AfterEach
  void tearDown() {
    q1 = null;
  }

  @Test
  void incTest() {
    var numbers = Stream.generate(() -> faker.number().randomNumber())
        .limit(faker.number().randomDigitNotZero())
        .toList();

    StepVerifier
        .create(q1.inc(numbers))
        .recordWith(ArrayList::new)
        .thenConsumeWhile(actual -> true)
        .consumeRecordedWith(actual ->{
          var actualList = new ArrayList<>(actual);
          IntStream.range(0, actual.size())
              .forEach(i -> assertEquals(actualList.get(i), numbers.get(i) + 1L));
        })
        .verifyComplete();
  }
}
