package org.example.bingowebflux.api.controller.round;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.bingowebflux.api.validation.ObjectIdIsValid;
import org.example.bingowebflux.api.validation.ValueOfEnum;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundCardOnlyResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundDrawResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundNumberResponseDTO;
import org.example.bingowebflux.domain.dto.round.RoundResponseDTO;
import org.example.bingowebflux.domain.enumeration.round.RoundStatus;
import org.example.bingowebflux.domain.exception.ExceptionResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.example.bingowebflux.domain.constant.Constants.*;
import static org.example.bingowebflux.domain.constant.Descriptions.*;
import static org.example.bingowebflux.domain.constant.ErrorMessages.GENERIC_REQUIRED;
import static org.example.bingowebflux.domain.constant.ErrorMessages.ROUND_STATUS_INVALID;
import static org.example.bingowebflux.domain.constant.Examples.ROUND_STATUS_EXAMPLE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("rounds")
@Validated
@Tag(name = ROUND_CONTROLLER, description = ROUND_CONTROLLER_DESCRIPTION)
@ApiResponse(
    responseCode = EXCEPTION_CODE_BAD_REQUEST,
    content = @Content(
        mediaType = APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = ExceptionResponse.class)
    )
)
@ApiResponse(
    responseCode = EXCEPTION_CODE_NOT_FOUND,
    content = @Content(
        mediaType = APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = ExceptionResponse.class)
    )
)
@ApiResponse(
    responseCode = EXCEPTION_CODE_UNPROCESSABLE_ENTITY,
    content = @Content(
        mediaType = APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = ExceptionResponse.class)
    )
)
public interface RoundAPI {
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_GET_LIST_SUMMARY,
      description = ROUND_GET_LIST_DESCRIPTION
  )
  Mono<PageResponseDTO<RoundResponseDTO>> getList(
      @Parameter(name = PAGE, description = PAGE_DESCRIPTION)
      @RequestParam(name = PAGE, defaultValue = DEFAULT_PAGE, required = false) int page,
      @Parameter(name = SIZE, description = SIZE_DESCRIPTION)
      @RequestParam(name = SIZE, defaultValue = DEFAULT_SIZE, required = false) int size,
      @Parameter(name = STATUS, description = ROUND_STATUS_DESCRIPTION, example = ROUND_STATUS_EXAMPLE,
          schema = @Schema(implementation = RoundStatus.class, enumAsRef = true, defaultValue = ROUND_STATUS_EXAMPLE)
      )
      @Valid @ValueOfEnum(enumClass = RoundStatus.class, message = ROUND_STATUS_INVALID)
      @RequestParam(name = STATUS, defaultValue = ROUND_STATUS_EXAMPLE, required = false) String status
  );

  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_GET_BY_ID_SUMMARY,
      description = ROUND_GET_BY_ID_DESCRIPTION
  )
  Mono<RoundResponseDTO> getById(
      @PathVariable
      @ObjectIdIsValid
      @NotBlank(message = GENERIC_REQUIRED)
      String id
  );

  @GetMapping(value = "/{id}/last-number", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_GET_LAST_NUMBER_SUMMARY,
      description = ROUND_GET_LAST_NUMBER_DESCRIPTION
  )
  Mono<RoundNumberResponseDTO> getLastNumber(
      @PathVariable
      @ObjectIdIsValid
      @NotBlank(message = GENERIC_REQUIRED)
      String id
  );

  @PostMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_CREATE_SUMMARY,
      description = ROUND_CREATE_DESCRIPTION
  )
  Mono<RoundResponseDTO> create();

  @PostMapping(value = "/{id}/generate-number", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_GENERATE_NUMBER_SUMMARY,
      description = ROUND_GENERATE_NUMBER_DESCRIPTION
  )
  Mono<RoundDrawResponseDTO> generateNumber(
      @PathVariable
      @ObjectIdIsValid
      @NotBlank(message = GENERIC_REQUIRED)
      String id
  );

  @PostMapping(value = "/{id}/generate-card/player/{playerId}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = ROUND_GENERATE_CARD_SUMMARY,
      description = ROUND_GENERATE_CARD_DESCRIPTION
  )
  Mono<RoundCardOnlyResponseDTO> generateCard(
      @PathVariable
      @NotBlank(message = GENERIC_REQUIRED)
      @ObjectIdIsValid
      String id,
      @PathVariable
      @NotBlank(message = GENERIC_REQUIRED)
      @ObjectIdIsValid
      String playerId
  );
}
