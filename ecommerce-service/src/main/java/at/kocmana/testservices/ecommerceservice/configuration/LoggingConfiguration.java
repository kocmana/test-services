package at.kocmana.testservices.ecommerceservice.configuration;

import at.kocmana.testservice.commons.requestid.RequestIdInterceptor;
import at.kocmana.testservice.commons.requestlogging.EnableRequestLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.server.UID;
import java.util.function.Supplier;

@EnableRequestLogging
@Configuration
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<UID> uidRequestIdInterceptor() {
    Supplier<UID> idGeneratorFunction = UID::new;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}
