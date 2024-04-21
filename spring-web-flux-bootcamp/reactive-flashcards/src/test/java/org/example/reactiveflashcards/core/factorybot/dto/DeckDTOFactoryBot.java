package org.example.reactiveflashcards.core.factorybot.dto;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.example.reactiveflashcards.domain.dto.CardDTO;
import org.example.reactiveflashcards.domain.dto.DeckDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@NoArgsConstructor(access = PRIVATE)
public class DeckDTOFactoryBot {

  public static DeckDTOFactoryBotBuilder builder() {
    return new DeckDTOFactoryBotBuilder();
  }


  public static class DeckDTOFactoryBotBuilder {
    private final String name;
    private final String info;
    private final String author;
    private final List<CardDTO> cards = new ArrayList<>();
    private final Faker faker = getFaker();

    public DeckDTOFactoryBotBuilder() {
      this.name = faker.name().name();
      this.info = faker.chuckNorris().fact();
      this.author = faker.name().fullName();
      generateCards();
    }

    private void generateCards() {
      cards.addAll(Stream.generate(() -> new CardDTO(faker.cat().name(), faker.color().name()))
          .limit(faker.number().numberBetween(3, 8))
          .collect(Collectors.toSet()));
    }

    public DeckDTO build() {
      return new DeckDTO(name, info, author, cards);
    }

  }

}
