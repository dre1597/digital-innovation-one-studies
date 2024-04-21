package org.example.reactiveflashcards.api.mapper;

import org.example.reactiveflashcards.api.controller.request.StudyRequest;
import org.example.reactiveflashcards.api.controller.response.AnswerQuestionResponse;
import org.example.reactiveflashcards.api.controller.response.QuestionResponse;
import org.example.reactiveflashcards.domain.document.Question;
import org.example.reactiveflashcards.domain.document.StudyDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudyMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "studyDeck.deckId", source = "deckId")
  @Mapping(target = "studyDeck.cards", ignore = true)
  @Mapping(target = "questions", ignore = true)
  @Mapping(target = "question", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  StudyDocument toDocument(final StudyRequest request);

  QuestionResponse toResponse(final Question question, final String id);

  AnswerQuestionResponse toResponse(final Question question);
}
