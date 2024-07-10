package org.example.bingowebflux.domain.dto.round;

import lombok.Builder;
import lombok.Data;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;

import java.util.ArrayList;
import java.util.List;

import static org.example.bingowebflux.domain.enumeration.round.RoundStatus.CREATED;

@Builder
@Data
public class RoundDTO {
  @Builder.Default
  List<RoundCardDTO> cards = new ArrayList<>();
  private String id;
  @Builder.Default
  private RoundStatus status = CREATED;
  private Integer lastDraw;
  @Builder.Default
  private List<Integer> drawnNumbers = new ArrayList<>();
  @Builder.Default
  private List<RoundCardDTO> winners = new ArrayList<>();
}
