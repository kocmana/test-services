package at.kocmana.testservices.productservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConfigurationProperties(prefix = "security")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class SecurityWhitelistProperties {

  private final List<String> whitelist;
}
