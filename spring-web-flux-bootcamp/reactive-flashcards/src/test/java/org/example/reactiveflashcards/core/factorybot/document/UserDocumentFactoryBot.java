package org.example.reactiveflashcards.core.factorybot.document;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.example.reactiveflashcards.domain.document.UserDocument;

import java.time.OffsetDateTime;

import static lombok.AccessLevel.PRIVATE;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@NoArgsConstructor(access = PRIVATE)
public class UserDocumentFactoryBot {

  public static UserDocumentFactoryBotBuilder builder() {
    return new UserDocumentFactoryBotBuilder();
  }

  public static class UserDocumentFactoryBotBuilder {

    private String id;
    private String name;
    private String email;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public UserDocumentFactoryBotBuilder() {
      var faker = getFaker();
      this.id = ObjectId.get().toString();
      this.name = faker.name().name();
      this.email = faker.internet().emailAddress();
      this.createdAt = OffsetDateTime.now();
      this.updatedAt = OffsetDateTime.now();
    }

    public UserDocumentFactoryBotBuilder preInsert() {
      this.id = null;
      this.createdAt = null;
      this.updatedAt = null;
      return this;
    }

    public UserDocumentFactoryBotBuilder preUpdate(final String id) {
      this.id = id;
      return this;
    }

    public UserDocument build() {
      return UserDocument.builder()
          .id(id)
          .name(name)
          .email(email)
          .createdAt(createdAt)
          .updatedAt(updatedAt)
          .build();
    }

  }

}
