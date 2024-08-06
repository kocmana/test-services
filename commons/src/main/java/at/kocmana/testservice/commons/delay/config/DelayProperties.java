package at.kocmana.testservice.commons.delay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.delay")
public record DelayProperties (
  boolean enableEndpointDelays,
  boolean enableServiceDelays,
  int serviceDelayMean,
  int serviceDelayStandardDeviation,
  boolean logDelays
  ){}
