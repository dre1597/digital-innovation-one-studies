package org.example.reactiveflashcards.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.api.controller.documentation.UserControllerDoc;
import org.example.reactiveflashcards.api.controller.request.UserPageRequest;
import org.example.reactiveflashcards.api.controller.request.UserRequest;
import org.example.reactiveflashcards.api.controller.response.UserPageResponse;
import org.example.reactiveflashcards.api.controller.response.UserResponse;
import org.example.reactiveflashcards.api.mapper.UserMapper;
import org.example.reactiveflashcards.core.validation.MongoId;
import org.example.reactiveflashcards.domain.service.UserService;
import org.example.reactiveflashcards.domain.service.query.UserQueryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("users")
@Slf4j
@AllArgsConstructor
public class UserController implements UserControllerDoc {

  private final UserService userService;
  private final UserQueryService userQueryService;
  private final UserMapper userMapper;

  @Override
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public Mono<UserResponse> save(@Valid @RequestBody final UserRequest request) {
    return userService.save(userMapper.toDocument(request))
        .doFirst(() -> log.info("==== Saving a user with follow data {}", request))
        .map(userMapper::toResponse);
  }

  @Override
  @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}")
  public Mono<UserResponse> findById(@PathVariable @Valid @MongoId(message = "{userController.id}") final String id) {
    return userQueryService.findById(id)
        .doFirst(() -> log.info("==== Finding a user with follow id {}", id))
        .map(userMapper::toResponse);
  }

  @Override
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public Mono<UserPageResponse> findOnDemand(@Valid final UserPageRequest request) {
    return userQueryService.findOnDemand(request)
        .doFirst(() -> log.info("==== Finding users on demand with follow request {}", request))
        .map(page -> userMapper.toResponse(page, request.limit()));
  }

  @Override
  @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{id}")
  public Mono<UserResponse> update(@PathVariable @Valid @MongoId(message = "{userController.id}") final String id,
                                   @Valid @RequestBody final UserRequest request) {
    return userService.update(userMapper.toDocument(request, id))
        .doFirst(() -> log.info("==== Updating a user with follow info [body: {}, id: {}]", request, id))
        .map(userMapper::toResponse);
  }

  @Override
  @DeleteMapping(value = "{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> delete(@PathVariable @Valid @MongoId(message = "{userController.id}") final String id) {
    return userService.delete(id)
        .doFirst(() -> log.info("==== Deleting a user with follow id {}", id));
  }
}
