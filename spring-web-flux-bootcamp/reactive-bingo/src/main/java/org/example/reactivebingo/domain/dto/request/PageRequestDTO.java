package org.example.reactivebingo.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.apache.commons.lang3.ObjectUtils;
import org.example.reactivebingo.domain.dto.enumeration.SortBy;
import org.example.reactivebingo.domain.dto.enumeration.SortDirection;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

public record PageRequestDTO<S extends SortBy>(
    @JsonProperty("sentence") String sentence,
    @PositiveOrZero @JsonProperty("page") Long page,
    @Min(1) @Max(50) @JsonProperty("limit")  Integer limit,
    @JsonProperty("sortBy") S sortBy,
    @JsonProperty("direction") SortDirection sortDirection
) {

  @Builder(toBuilder = true)
  public PageRequestDTO {
    page = ObjectUtils.defaultIfNull(page, 0L);
    limit = ObjectUtils.defaultIfNull(limit, 10);
    sortBy = ObjectUtils.defaultIfNull(sortBy, null);
    sortDirection = ObjectUtils.defaultIfNull(sortDirection, SortDirection.ASC);
  }

  @Schema(hidden = true)
  public Sort getSort() {
    return sortDirection.equals(SortDirection.DESC) ? Sort.by(sortBy.getField()).descending() : Sort.by(sortBy.getField()).ascending();
  }

  @Schema(hidden = true)
  public Long getSkip() {
    return page * limit;
  }
}
