package org.example.reactiveflashcards.api.mapper;

import org.example.reactiveflashcards.api.controller.request.DeckRequest;
import org.example.reactiveflashcards.api.controller.response.DeckResponse;
import org.example.reactiveflashcards.domain.document.DeckDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeckMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  DeckDocument toDocument(final DeckRequest request);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  DeckDocument toDocument(final DeckRequest request, final String id);

  DeckResponse toResponse(final DeckDocument document);
}
