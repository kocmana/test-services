package at.kocmana.testservice.commons.requestlogging;

import at.kocmana.testservice.commons.requestlogging.configuration.LoggingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = LoggingConfiguration.class)
public @interface EnableRequestLogging {

}
