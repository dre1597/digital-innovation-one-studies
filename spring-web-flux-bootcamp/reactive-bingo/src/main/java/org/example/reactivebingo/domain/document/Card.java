package org.example.reactivebingo.domain.document;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.Set;

public record Card(
    @Field("player_id") String playerId,
    Set<Integer> numbers,
    @Field("marked_numbers") Set<Integer> markedNumbers,
    @CreatedDate @Field("created_at") OffsetDateTime createdAt
) {
}
