package org.example.reactivebingo.domain.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerSortBy implements SortBy {
  NAME("name"), EMAIL("prize"), DATE("created_at");

  private final String field;
}
