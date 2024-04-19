package org.example.reactiveflashcards.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.api.mapper.UserMapper;
import org.example.reactiveflashcards.api.request.UserRequest;
import org.example.reactiveflashcards.api.response.UserResponse;
import org.example.reactiveflashcards.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/users")
@Slf4j
@AllArgsConstructor
public class UserController {
  private final UserService service;
  private final UserMapper mapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<UserResponse> save(@Valid @RequestBody final UserRequest request) {
    return service.save(mapper.toDocument(request))
        .doFirst(() -> log.info("Try to save a follow document {}", request))
        .map(mapper::toResponse);
  }
}

