package at.kocmana.testservices.customerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "security")
public record SecurityWhitelistProperties(
        List<String> whitelist
) {}
