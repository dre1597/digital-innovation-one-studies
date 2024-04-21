package org.example.reactiveflashcards.domain.mapper;

import org.example.reactiveflashcards.domain.document.Card;
import org.example.reactiveflashcards.domain.document.DeckDocument;
import org.example.reactiveflashcards.domain.dto.CardDTO;
import org.example.reactiveflashcards.domain.dto.DeckDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface DeckDomainMapper {
  @Mapping(target = "description", source = "info")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  DeckDocument toDocument(final DeckDTO request);

  @Mapping(target = "back", source = "answer")
  @Mapping(target = "front", source = "ask")
  Card toDocument(final CardDTO dto);
}
