package org.example.bingowebflux.domain.document;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder(toBuilder = true)
@Document(collection = "round")
public record RoundDocument(
    @Id ObjectId id,
    RoundStatus status,
    Integer lastDraw,
    List<Integer> drawnNumbers,
    List<RoundCardDocument> winners,
    List<RoundCardDocument> cards
) {
}
