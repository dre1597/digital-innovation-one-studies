package org.example.bingowebflux.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static org.example.bingowebflux.domain.constant.Descriptions.*;

@Builder(toBuilder = true)
public record PageResponseDTO<T>(
    @Schema(description = PAGE_RESPONSE_CONTENT)
    List<T> content,

    @Schema(description = PAGE_RESPONSE_LAST)
    boolean last,

    @Schema(description = PAGE_RESPONSE_TOTAL_PAGES)
    Integer totalPages,

    @Schema(description = PAGE_RESPONSE_TOTAL_ELEMENTS)
    Long totalElements,

    @Schema(description = PAGE_RESPONSE_HAS_NEXT)
    boolean hasNext,

    @Schema(description = PAGE_RESPONSE_NUMBER)
    Integer number,

    @Schema(description = PAGE_RESPONSE_SIZE)
    Integer size
) {
}
