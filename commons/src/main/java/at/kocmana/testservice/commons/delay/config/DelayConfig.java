package at.kocmana.testservice.commons.delay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"at.kocmana.testservice.commons.delay"})
public class DelayConfig {

  DelayProperties delayProperties;

  @Autowired
  public DelayConfig(DelayProperties delayProperties) {
    this.delayProperties = delayProperties;
  }
}
