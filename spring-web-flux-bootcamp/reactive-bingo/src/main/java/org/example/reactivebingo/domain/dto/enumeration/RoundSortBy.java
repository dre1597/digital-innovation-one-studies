package org.example.reactivebingo.domain.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoundSortBy implements SortBy {
  NAME("name"), PRIZE("prize"), DATE("created_at");

  private final String field;
}
