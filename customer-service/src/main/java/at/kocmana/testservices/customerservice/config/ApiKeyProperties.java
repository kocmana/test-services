package at.kocmana.testservices.customerservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConstructorBinding
@ConfigurationProperties(prefix = "customerservice.api-key")
@AllArgsConstructor
@Getter
public class ApiKeyProperties {

  private final String header;
  private final List<String> values;
}


