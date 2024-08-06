package at.kocmana.testservices.productservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "security")
public record SecurityWhitelistProperties(
        List<String> whitelist
) {
}
