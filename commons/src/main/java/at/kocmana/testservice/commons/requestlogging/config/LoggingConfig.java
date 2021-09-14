package at.kocmana.testservice.commons.requestlogging.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"at.kocmana.testservice.commons.requestlogging"})
public class LoggingConfig {

}
