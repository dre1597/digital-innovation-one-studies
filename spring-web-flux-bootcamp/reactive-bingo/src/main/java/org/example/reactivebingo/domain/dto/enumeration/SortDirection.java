package org.example.reactivebingo.domain.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum SortDirection {
  ASC(Sort.Direction.ASC),
  DESC(Sort.Direction.DESC);

  private final Sort.Direction direction;
}
