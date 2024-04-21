package org.example.reactiveflashcards.api.mapper;

import org.example.reactiveflashcards.api.controller.request.UserRequest;
import org.example.reactiveflashcards.api.controller.response.UserPageResponse;
import org.example.reactiveflashcards.api.controller.response.UserResponse;
import org.example.reactiveflashcards.domain.document.UserDocument;
import org.example.reactiveflashcards.domain.dto.UserPageDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  UserDocument toDocument(final UserRequest request);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  UserDocument toDocument(final UserRequest request, final String id);

  UserResponse toResponse(final UserDocument document);

  UserPageResponse toResponse(final UserPageDocument document, final Integer limit);
}
