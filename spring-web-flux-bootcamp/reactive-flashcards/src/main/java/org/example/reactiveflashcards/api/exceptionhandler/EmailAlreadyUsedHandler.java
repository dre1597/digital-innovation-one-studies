package org.example.reactiveflashcards.api.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.reactiveflashcards.domain.exception.EmailAlreadyUsedException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Component
public class EmailAlreadyUsedHandler extends AbstractHandleException<EmailAlreadyUsedException> {

  public EmailAlreadyUsedHandler(final ObjectMapper mapper) {
    super(mapper);
  }

  @Override
  Mono<Void> handlerException(final ServerWebExchange exchange, final EmailAlreadyUsedException ex) {
    return Mono.fromCallable(() -> {
          prepareExchange(exchange, BAD_REQUEST);
          return ex.getMessage();
        }).map(message -> buildError(BAD_REQUEST, message))
        .doFirst(() -> log.error("==== EmailAlreadyUsedException", ex))
        .flatMap(response -> writeResponse(exchange, response));
  }
}
