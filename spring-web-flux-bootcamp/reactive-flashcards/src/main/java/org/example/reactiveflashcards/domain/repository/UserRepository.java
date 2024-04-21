package org.example.reactiveflashcards.domain.repository;

import org.example.reactiveflashcards.domain.document.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDocument, String> {
  Mono<UserDocument> findByEmail(final String email);
}
