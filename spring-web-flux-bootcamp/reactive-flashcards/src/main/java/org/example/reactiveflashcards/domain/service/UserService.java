package org.example.reactiveflashcards.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.domain.document.UserDocument;
import org.example.reactiveflashcards.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;

  public Mono<UserDocument> save(final UserDocument document) {
    return userRepository.save(document)
        .doFirst(() -> log.info("Try to save a follow document {}", document));
  }
}
