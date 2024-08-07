package org.example.reactiveflashcards.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.util.Objects;

public record QuestionDTO(
    String asked,
    OffsetDateTime askedIn,
    String answered,
    OffsetDateTime answeredIn,
    String expected
) {
  public static QuestionBuilder builder() {
    return new QuestionBuilder();
  }

  public Boolean isAnswered() {
    return Objects.nonNull(answeredIn);
  }

  public Boolean isNotAnswered() {
    return Objects.isNull(answeredIn);
  }

  public Boolean isCorrect() {
    return isAnswered() && answered.equals(expected);
  }

  public QuestionBuilder toBuilder() {
    return new QuestionBuilder(asked, askedIn, answered, answeredIn, expected);
  }

  @AllArgsConstructor
  @NoArgsConstructor
  public static class QuestionBuilder {
    private String asked;
    private OffsetDateTime askedIn;
    private String answered;
    private OffsetDateTime answeredIn;
    private String expected;

    public QuestionBuilder asked(final String asked) {
      if (StringUtils.isNotBlank(asked)) {
        this.asked = asked;
        this.askedIn = OffsetDateTime.now();
      }
      return this;
    }

    public QuestionBuilder answered(final String answered) {
      if (StringUtils.isNotBlank(answered)) {
        this.answered = answered;
        this.answeredIn = OffsetDateTime.now();
      }
      return this;
    }

    public QuestionBuilder expected(final String expected) {
      this.expected = expected;
      return this;
    }

    public QuestionDTO build() {
      return new QuestionDTO(asked, askedIn, answered, answeredIn, expected);
    }
  }
}
