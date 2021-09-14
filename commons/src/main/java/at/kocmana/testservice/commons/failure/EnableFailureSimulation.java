package at.kocmana.testservice.commons.failure;

import at.kocmana.testservice.commons.failure.config.FailureConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = FailureConfig.class)
public @interface EnableFailureSimulation {

}
