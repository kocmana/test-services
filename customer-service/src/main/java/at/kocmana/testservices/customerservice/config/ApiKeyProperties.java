package at.kocmana.testservices.customerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "customerservice.api-key")
public record ApiKeyProperties(
        String header,
        List<String> values
) {}


