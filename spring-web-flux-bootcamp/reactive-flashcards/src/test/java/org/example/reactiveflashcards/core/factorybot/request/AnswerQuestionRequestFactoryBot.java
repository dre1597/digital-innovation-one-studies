package org.example.reactiveflashcards.core.factorybot.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.example.reactiveflashcards.api.controller.request.AnswerQuestionRequest;

import static lombok.AccessLevel.PRIVATE;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@NoArgsConstructor(access = PRIVATE)
public class AnswerQuestionRequestFactoryBot {

  public static AnswerQuestionRequestFactoryBotBuilder builder() {
    return new AnswerQuestionRequestFactoryBotBuilder();
  }

  public static class AnswerQuestionRequestFactoryBotBuilder {
    private final Faker faker = getFaker();
    private String answer;

    public AnswerQuestionRequestFactoryBotBuilder() {
      this.answer = faker.lorem().word();
    }

    public AnswerQuestionRequestFactoryBotBuilder blankAnswer() {
      this.answer = faker.bool().bool() ? null : " ";
      return this;
    }

    public AnswerQuestionRequestFactoryBotBuilder longAnswer() {
      this.answer = faker.lorem().sentence(256);
      return this;
    }

    public AnswerQuestionRequest build() {
      return AnswerQuestionRequest.builder()
          .answer(answer)
          .build();
    }

  }


}
