package org.example.reactiveflashcards.core.factorybot.request;

import com.github.javafaker.Faker;
import org.example.reactiveflashcards.api.controller.request.UserPageRequest;
import org.example.reactiveflashcards.api.controller.request.UserSortBy;
import org.example.reactiveflashcards.api.controller.request.UserSortDirection;

import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;
import static org.example.reactiveflashcards.core.factorybot.RandomData.randomEnum;

public class UserPageRequestFactoryBot {

  public static UserPageRequestFactoryBotBuilder builder() {
    return new UserPageRequestFactoryBotBuilder();
  }

  public static class UserPageRequestFactoryBotBuilder {

    private final String sentence;
    private final UserSortBy sortBy;
    private final UserSortDirection sortDirection;
    private final Faker faker = getFaker();
    private Long page;
    private Integer limit;

    public UserPageRequestFactoryBotBuilder() {
      this.sentence = faker.lorem().sentence();
      this.page = faker.number().numberBetween(0L, 3L);
      this.limit = faker.number().numberBetween(20, 40);
      this.sortBy = randomEnum(UserSortBy.class);
      this.sortDirection = randomEnum(UserSortDirection.class);
    }

    public UserPageRequestFactoryBotBuilder negativePage() {
      this.page = faker.number().numberBetween(Long.MIN_VALUE, 0);
      return this;
    }

    public UserPageRequestFactoryBotBuilder lessThanZeroLimit() {
      this.limit = faker.number().numberBetween(Integer.MIN_VALUE, 1);
      return this;
    }

    public UserPageRequestFactoryBotBuilder greaterThanFiftyLimit() {
      this.limit = faker.number().numberBetween(51, Integer.MAX_VALUE);
      return this;
    }

    public UserPageRequest build() {
      return UserPageRequest.builder()
          .sentence(sentence)
          .page(page)
          .limit(limit)
          .sortBy(sortBy)
          .sortDirection(sortDirection)
          .build();
    }

  }

}
