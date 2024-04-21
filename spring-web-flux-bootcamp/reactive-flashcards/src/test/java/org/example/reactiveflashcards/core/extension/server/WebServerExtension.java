package org.example.reactiveflashcards.core.extension.server;

import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.extension.*;

@Slf4j
public class WebServerExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private MockWebServer mockWebServer;

  @Override
  public void beforeEach(final ExtensionContext context) {
    try {
      log.info("=== Starting mock web server");
      mockWebServer = new MockWebServer();
      mockWebServer.start();
    } catch (final Exception ex) {
      log.warn("==== ERROR - Can't start mock web server", ex);
    }
  }

  @Override
  public void afterEach(final ExtensionContext context) {
    try {
      mockWebServer.shutdown();
    } catch (final Exception ex) {
      log.warn("==== ERROR - Can't finish mock web server", ex);
    }
  }

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(WebServer.class);
  }

  @Override
  public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) throws ParameterResolutionException {
    if (parameterContext.getParameter().isAnnotationPresent(WebServer.class)) {
      return mockWebServer;
    } else {
      return null;
    }
  }
}
