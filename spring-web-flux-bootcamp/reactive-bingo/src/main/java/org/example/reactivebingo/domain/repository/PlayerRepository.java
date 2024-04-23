package org.example.reactivebingo.domain.repository;

import org.example.reactivebingo.domain.document.PlayerDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<PlayerDocument, String> {
}
