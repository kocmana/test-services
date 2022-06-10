package at.kocmana.testservice.commons.requestlogging.configuration;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"at.kocmana.testservice.commons.requestlogging"})
public class LoggingConfiguration {

}
