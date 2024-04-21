package org.example.reactiveflashcards.domain.repository;

import org.example.reactiveflashcards.domain.document.StudyDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudyRepository extends ReactiveMongoRepository<StudyDocument, String> {
  Mono<StudyDocument> findByUserIdAndCompleteFalseAndStudyDeck_DeckId(final String userId, final String deckId);
}
