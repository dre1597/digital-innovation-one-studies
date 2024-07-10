package org.example.bingowebflux.domain.document;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(toBuilder = true)
@Document(collection = "player")
public record PlayerDocument(
    @Id ObjectId id,
    String nickname
) {
}
