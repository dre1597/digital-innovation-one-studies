package org.example.bingowebflux.domain.dto.round;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoundPlayerDTO {
  private String id;
  private String nickname;
}

