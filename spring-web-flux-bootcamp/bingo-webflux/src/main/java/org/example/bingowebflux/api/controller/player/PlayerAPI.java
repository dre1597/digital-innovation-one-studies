package org.example.bingowebflux.api.controller.player;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.bingowebflux.api.validation.ObjectIdIsValid;
import org.example.bingowebflux.domain.dto.PageResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerResponseDTO;
import org.example.bingowebflux.domain.dto.player.PlayerUpdateRequestDTO;
import org.example.bingowebflux.domain.exception.ExceptionResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.example.bingowebflux.domain.constant.Constants.*;
import static org.example.bingowebflux.domain.constant.Descriptions.*;
import static org.example.bingowebflux.domain.constant.ErrorMessages.GENERIC_REQUIRED;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("players")
@Validated
@Tag(name = PLAYER_CONTROLLER, description = PLAYER_CONTROLLER_DESCRIPTION)
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
public interface PlayerAPI {
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = PLAYER_GET_LIST_SUMMARY,
      description = PLAYER_GET_LIST_DESCRIPTION
  )
  Mono<PageResponseDTO<PlayerResponseDTO>> getList(
      @Parameter(name = PAGE, description = PAGE_DESCRIPTION)
      @RequestParam(name = PAGE, defaultValue = DEFAULT_PAGE, required = false) int page,
      @Parameter(name = SIZE, description = SIZE_DESCRIPTION)
      @RequestParam(name = SIZE, defaultValue = DEFAULT_SIZE, required = false) int size
  );

  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = PLAYER_GET_BY_ID_SUMMARY,
      description = PLAYER_GET_BY_ID_DESCRIPTION,
      parameters = @Parameter(name = ID, description = PLAYER_FIELD_ID_DESCRIPTION)
  )
  Mono<PlayerResponseDTO> getById(
      @PathVariable
      @NotBlank(message = GENERIC_REQUIRED)
      @ObjectIdIsValid
      String id
  );

  @PostMapping(value = "/{nickname}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  @Operation(
      summary = PLAYER_POST_SUMMARY,
      description = PLAYER_POST_DESCRIPTION,
      parameters = @Parameter(name = NICKNAME, description = PLAYER_FIELD_NICKNAME_DESCRIPTION)
  )
  Mono<PlayerResponseDTO> create(
      @PathVariable
      @NotBlank(message = GENERIC_REQUIRED)
      String nickname
  );

  @PutMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(
      summary = PLAYER_PUT_SUMMARY,
      description = PLAYER_PUT_DESCRIPTION
  )
  Mono<PlayerResponseDTO> update(
      @Valid
      @RequestBody
      PlayerUpdateRequestDTO request
  );

  @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(NO_CONTENT)
  @Operation(
      summary = PLAYER_DELETE_SUMMARY,
      description = PLAYER_DELETE_DESCRIPTION,
      parameters = @Parameter(name = ID, description = PLAYER_FIELD_ID_DESCRIPTION)
  )
  Mono<Void> delete(
      @NotBlank(message = GENERIC_REQUIRED)
      @ObjectIdIsValid
      @PathVariable String id
  );
}
