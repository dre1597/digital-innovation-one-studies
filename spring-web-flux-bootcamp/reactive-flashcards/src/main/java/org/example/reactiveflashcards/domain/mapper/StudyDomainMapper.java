package org.example.reactiveflashcards.domain.mapper;

import org.example.reactiveflashcards.domain.document.Card;
import org.example.reactiveflashcards.domain.document.Question;
import org.example.reactiveflashcards.domain.document.StudyCard;
import org.example.reactiveflashcards.domain.document.StudyDocument;
import org.example.reactiveflashcards.domain.dto.QuestionDTO;
import org.example.reactiveflashcards.domain.dto.StudyCardDTO;
import org.example.reactiveflashcards.domain.dto.StudyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudyDomainMapper {
  StudyCard toStudyCard(final Card cards);

  default Question generateRandomQuestion(final Set<StudyCard> cards) {
    var values = new ArrayList<>(cards);
    var random = new Random();
    var position = random.nextInt(values.size());
    return toQuestion(values.get(position));
  }

  @Mapping(target = "asked", source = "front")
  @Mapping(target = "answered", ignore = true)
  @Mapping(target = "expected", source = "back")
  Question toQuestion(final StudyCard card);

  @Mapping(target = "asked", source = "front")
  @Mapping(target = "answered", ignore = true)
  @Mapping(target = "expected", source = "back")
  QuestionDTO toQuestion(final StudyCardDTO card);

  default StudyDocument answer(final StudyDocument document, final String answer) {
    var currentQuestion = document.getLastPendingQuestion();
    var questions = document.questions();
    var curIndexQuestion = questions.indexOf(currentQuestion);
    currentQuestion = currentQuestion.toBuilder().answered(answer).build();
    questions.set(curIndexQuestion, currentQuestion);
    return document.toBuilder().questions(questions).build();
  }

  @Mapping(target = "question", ignore = true)
  StudyDTO toDTO(final StudyDocument document, final List<String> remainAsks);

  @Mapping(target = "question", ignore = true)
  StudyDocument toDocument(final StudyDTO dto);
}
