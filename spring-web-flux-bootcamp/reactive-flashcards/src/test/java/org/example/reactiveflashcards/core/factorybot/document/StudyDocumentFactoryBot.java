package org.example.reactiveflashcards.core.factorybot.document;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.example.reactiveflashcards.domain.document.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;
import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@NoArgsConstructor(access = PRIVATE)
public class StudyDocumentFactoryBot {

  public static StudyDocumentFactoryBotBuilder builder(final String userId, final DeckDocument deck) {
    return new StudyDocumentFactoryBotBuilder(userId, deck);
  }

  public static class StudyDocumentFactoryBotBuilder {
    private final List<Question> questions = new ArrayList<>();
    private final Faker faker = getFaker();
    private String id;
    private String userId;
    private StudyDeck studyDeck = StudyDeck.builder().build();
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public StudyDocumentFactoryBotBuilder(final String userId, final DeckDocument deck) {
      this.id = ObjectId.get().toString();
      this.userId = userId;
      generateStudy(deck);
      generateRandomQuestionWithWrongAnswer();
      generateRandomQuestionWithRightAnswer();
      generateNonAskedRandomQuestion();
      this.createdAt = OffsetDateTime.now();
      this.updatedAt = OffsetDateTime.now();
    }

    public StudyDocumentFactoryBotBuilder finishedStudy() {
      this.questions.clear();
      studyDeck.cards().forEach(c -> questions.add(Question.builder()
          .asked(c.front())
          .answered(c.back())
          .expected(c.back())
          .build()));
      return this;
    }

    public StudyDocumentFactoryBotBuilder pendingQuestions(final Integer remain) {
      this.questions.clear();
      studyDeck.cards().forEach(c -> questions.add(Question.builder()
          .asked(c.front())
          .answered(c.back())
          .expected(c.back())
          .build()));
      var index = questions.size() - remain;
      while (index < questions.size()) {
        var selectedStudy = questions.get(index);
        selectedStudy = Question.builder()
            .asked(selectedStudy.asked())
            .expected(selectedStudy.expected())
            .build();
        questions.set(index, selectedStudy);
        ++index;
      }
      var positionsToRemove = remain - 1;
      while (positionsToRemove != 0) {
        questions.remove(questions.size() - positionsToRemove);
        --positionsToRemove;
      }
      return this;
    }

    public StudyDocumentFactoryBotBuilder preInsert() {
      this.id = null;
      this.createdAt = null;
      this.updatedAt = null;
      generateNonAskedRandomQuestion();
      return this;
    }

    public StudyDocument build() {
      return StudyDocument.builder()
          .id(id)
          .userId(userId)
          .studyDeck(studyDeck)
          .questions(questions)
          .createdAt(createdAt)
          .updatedAt(updatedAt)
          .build();
    }

    private void generateNonAskedRandomQuestion() {
      var values = new ArrayList<>(studyDeck.cards());
      var random = new Random();
      var position = random.nextInt(values.size());
      var card = values.get(position);
      questions.add(Question.builder().asked(card.front()).expected(card.back()).build());
    }

    private void generateRandomQuestionWithWrongAnswer() {
      var values = new ArrayList<>(studyDeck.cards());
      var random = new Random();
      var position = random.nextInt(values.size());
      var card = values.get(position);
      questions.add(Question.builder()
          .asked(card.front())
          .answered(faker.app().name())
          .expected(card.back())
          .build());
    }

    private void generateRandomQuestionWithRightAnswer() {
      var values = new ArrayList<>(studyDeck.cards());
      var random = new Random();
      var position = random.nextInt(values.size());
      var card = values.get(position);
      questions.add(Question.builder()
          .asked(card.front())
          .answered(card.back())
          .expected(card.back())
          .build());
    }

    private void generateStudy(final DeckDocument deck) {
      var studyCards = deck.cards().stream().map(c -> StudyCard.builder()
          .front(c.front())
          .back(c.back())
          .build()).collect(Collectors.toSet());
      this.studyDeck = this.studyDeck.toBuilder()
          .deckId(deck.id())
          .cards(studyCards)
          .build();
    }

  }

}
