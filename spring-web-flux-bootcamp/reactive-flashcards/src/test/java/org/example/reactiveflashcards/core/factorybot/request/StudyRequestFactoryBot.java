package org.example.reactiveflashcards.core.factorybot.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.example.reactiveflashcards.api.controller.request.StudyRequest;

import static lombok.AccessLevel.PRIVATE;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@NoArgsConstructor(access = PRIVATE)
public class StudyRequestFactoryBot {

  public static StudyRequestFactoryBotBuilder builder() {
    return new StudyRequestFactoryBotBuilder();
  }

  public static class StudyRequestFactoryBotBuilder {
    private final Faker faker = getFaker();
    private String deckId;
    private String userId;

    public StudyRequestFactoryBotBuilder() {
      this.deckId = ObjectId.get().toString();
      this.userId = ObjectId.get().toString();
    }

    public StudyRequestFactoryBotBuilder invalidDeckId() {
      this.deckId = faker.bool().bool() ? null : faker.lorem().word();
      return this;
    }

    public StudyRequestFactoryBotBuilder invalidUserId() {
      this.userId = faker.bool().bool() ? null : faker.lorem().word();
      return this;
    }

    public StudyRequest build() {
      return StudyRequest.builder()
          .deckId(deckId)
          .userId(userId)
          .build();
    }
  }
}
