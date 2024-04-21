package org.example.reactiveflashcards.api.controller;

import com.github.javafaker.Faker;
import org.example.reactiveflashcards.ReactiveFlashcardsApplication;
import org.example.reactiveflashcards.api.exceptionhandler.*;
import org.example.reactiveflashcards.core.mongo.provider.OffsetDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.example.reactiveflashcards.core.factorybot.RandomData.getFaker;

@ActiveProfiles("test")
@ContextConfiguration(classes = {OffsetDateTimeProvider.class, ApiExceptionHandler.class,
    DeckInStudyHandler.class, EmailAlreadyUsedHandler.class, MethodNotAllowedHandler.class, NotFoundHandler.class,
    ConstraintViolationHandler.class, WebExchangeBindHandler.class, ResponseStatusHandler.class,
    ReactiveFlashcardsHandler.class, GenericHandler.class, JsonProcessingHandler.class, ReactiveFlashcardsApplication.class
})
public abstract class AbstractControllerTest {

  protected final static Faker faker = getFaker();
  @MockBean
  protected MappingMongoConverter mappingMongoConverter;
  @Autowired
  protected ApplicationContext applicationContext;

}

