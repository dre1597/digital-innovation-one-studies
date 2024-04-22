package org.example.reactivebingo.domain.document;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

public record DrawnNumber(
    Integer number,
    @CreatedDate @Field("created_at") OffsetDateTime createdAt
) {

  @Builder(toBuilder = true)
  public DrawnNumber {

  }
}
