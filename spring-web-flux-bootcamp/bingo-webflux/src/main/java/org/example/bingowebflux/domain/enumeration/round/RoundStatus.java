package org.example.bingowebflux.domain.enumeration.round;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoundStatus {
  CREATED, RUNNING, FINISHED;

  public static RoundStatus getByName(String name) {
    return Arrays.stream(values())
        .filter(r -> r.name().equals(name))
        .findFirst().orElseThrow();
  }
}
