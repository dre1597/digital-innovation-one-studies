package org.example.reactiveflashcards.api.mapper;

import org.example.reactiveflashcards.api.controller.request.UserRequest;
import org.example.reactiveflashcards.api.controller.response.UserResponse;
import org.example.reactiveflashcards.domain.document.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  UserDocument toDocument(final UserRequest request);

  UserResponse toResponse(final UserDocument document);
}
