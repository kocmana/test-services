package at.kocmana.testservices.customerservice.config;

import at.kocmana.testservice.commons.requestid.RequestIdInterceptor;
import at.kocmana.testservice.commons.requestlogging.EnableRequestLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.function.Supplier;

@Configuration
@EnableRequestLogging
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<Long> epochRequestIdInterceptor() {
    Supplier<Long> idGeneratorFunction = Instant.now()::getEpochSecond;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}

