package at.kocmana.testservice.commons.delay;

import at.kocmana.testservice.commons.delay.config.DelayConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value = RUNTIME)
@Target(value = TYPE)
@Documented
@Import(value = DelayConfig.class)
public @interface EnableDelaySimulation {

}
