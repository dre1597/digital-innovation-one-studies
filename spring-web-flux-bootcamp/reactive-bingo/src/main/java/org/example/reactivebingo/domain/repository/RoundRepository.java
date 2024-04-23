package org.example.reactivebingo.domain.repository;

import org.example.reactivebingo.domain.document.RoundDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends ReactiveMongoRepository<RoundDocument, String> {
}
