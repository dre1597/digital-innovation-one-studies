package org.example.bingowebflux.domain.repository.round;

import org.bson.types.ObjectId;
import org.example.bingowebflux.domain.document.RoundDocument;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoundRepository extends ReactiveMongoRepository<RoundDocument, ObjectId> {
  Mono<Long> countByStatus(RoundStatus status);
  Mono<Boolean> existsById(ObjectId id);
  Flux<RoundDocument> findByStatus(RoundStatus status, Pageable pageable);
}
