package org.example.bingowebflux.domain.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static org.example.bingowebflux.domain.constant.Descriptions.EXCEPTION_FIELD_RESPONSE_MESSAGE;
import static org.example.bingowebflux.domain.constant.Descriptions.EXCEPTION_FIELD_RESPONSE_NAME;

@Builder(toBuilder = true)
public record ErrorFieldResponse(
    @Schema(description = EXCEPTION_FIELD_RESPONSE_NAME)
    String name,
    @Schema(description = EXCEPTION_FIELD_RESPONSE_MESSAGE)
    String message
) {
}
