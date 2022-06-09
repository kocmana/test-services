package at.kocmana.testservices.productservice.config;

import at.kocmana.testservice.commons.requestid.RequestIdInterceptor;
import at.kocmana.testservice.commons.requestlogging.EnableRequestLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
@EnableRequestLogging
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<UUID> uuidRequestIdInterceptor() {
    Supplier<UUID> idGeneratorFunction = UUID::randomUUID;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}

