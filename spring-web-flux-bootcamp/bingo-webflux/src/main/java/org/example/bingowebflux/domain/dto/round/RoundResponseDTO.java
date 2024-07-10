package org.example.bingowebflux.domain.dto.round;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.example.bingowebflux.domain.constant.Descriptions.*;

@Builder(toBuilder = true)
@JsonInclude(NON_NULL)
public record RoundResponseDTO(

    @Schema(description = ROUND_ID_DESCRIPTION)
    String id,

    @Schema(description = ROUND_STATUS_DESCRIPTION)
    RoundStatus status,

    @Schema(description = ROUND_DRAWN_NUMBERS)
    List<Integer> drawnNumbers,

    @Schema(description = ROUND_WINNERS)
    List<RoundCardResponseDTO> winners,

    @Schema(description = ROUND_CARDS)
    List<RoundCardResponseDTO> cards

) {
}
