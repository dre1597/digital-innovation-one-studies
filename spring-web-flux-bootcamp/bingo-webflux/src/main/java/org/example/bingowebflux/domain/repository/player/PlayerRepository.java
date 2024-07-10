package org.example.bingowebflux.domain.repository.player;

import org.bson.types.ObjectId;
import org.example.bingowebflux.domain.document.PlayerDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<PlayerDocument, ObjectId> {
  Mono<Boolean> existsByNickname(String nickname);
  Mono<Boolean> existsById(ObjectId id);
  Flux<PlayerDocument> findByIdNotNullOrderByNicknameAsc(Pageable pageable);
}
