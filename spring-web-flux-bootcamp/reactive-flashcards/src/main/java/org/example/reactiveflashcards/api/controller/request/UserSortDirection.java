package org.example.reactiveflashcards.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum UserSortDirection {
  ASC(Sort.Direction.ASC),
  DESC(Sort.Direction.DESC);

  private final Sort.Direction direction;
}
