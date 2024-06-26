package org.example.reactivebingo.domain.document;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

@Document(collection = "players")
public record PlayerDocument(
    @Id String id,
    String name,
    @CreatedDate @Field("created_at") OffsetDateTime createdAt,
    @LastModifiedDate @Field("updated_at") OffsetDateTime updatedAt
) {

  @Builder(toBuilder = true)
  public PlayerDocument {

  }
}
