package org.example.bingowebflux.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.example.bingowebflux.domain.constant.Descriptions.*;


@JsonInclude(NON_NULL)
public record ExceptionResponse(
    @Schema(description = EXCEPTION_RESPONSE_TIMESTAMP)
    LocalDateTime timestamp,
    @Schema(description = EXCEPTION_RESPONSE_ERROR_DESCRIPTION)
    String errorDescription,
    @Schema(description = EXCEPTION_RESPONSE_LIST_FIELD)
    List<ErrorFieldResponse> fields,
    @Schema(description = EXCEPTION_RESPONSE_HTTP_STATUS)
    Integer status
) {
  @Builder(toBuilder = true)
  public ExceptionResponse {
    timestamp = LocalDateTime.now();
  }
}
