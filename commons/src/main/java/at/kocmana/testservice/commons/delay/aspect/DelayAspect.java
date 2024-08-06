package at.kocmana.testservice.commons.delay.aspect;

import at.kocmana.testservice.commons.delay.annotation.FixedEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.annotation.ProbabilisticEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.config.DelayProperties;
import at.kocmana.testservice.commons.delay.model.Delay;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static at.kocmana.testservice.commons.delay.DelayFactory.createDelayFromAnnotation;
import static at.kocmana.testservice.commons.util.AnnotationUtils.extractAnnotation;

@Aspect
@Component
@ConditionalOnProperty(prefix = "service.delay", name = "enable-endpoint-delays",
    havingValue = "true")
@Slf4j
public class DelayAspect {

  private final DelayProperties delayProperties;

  @Autowired
  public DelayAspect(DelayProperties delayProperties) {
    this.delayProperties = delayProperties;
    log.warn("Delay annotation active.");
  }

  @Around("@annotation(at.kocmana.testservice.commons.delay.annotation.FixedEndpointDelaySimulation)")
  public Object delayExecutionWithFixedDuration(ProceedingJoinPoint joinPoint) throws Throwable {
    var annotation = extractAnnotation(joinPoint, FixedEndpointDelaySimulation.class);
    var delay = createDelayFromAnnotation(annotation);

    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation)")
  public Object delayExecutionWithNormallyDistributedDuration(ProceedingJoinPoint joinPoint)
      throws Throwable {
    NormallyDistributedEndpointDelaySimulation annotation = extractAnnotation(joinPoint,
        NormallyDistributedEndpointDelaySimulation.class);
    var delay = createDelayFromAnnotation(annotation);
    delayResponse(delay);

    return joinPoint.proceed();
  }

  @Around("@annotation(at.kocmana.testservice.commons.delay.annotation.ProbabilisticEndpointDelaySimulation)")
  public Object delayExecutionWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    ProbabilisticEndpointDelaySimulation annotation = extractAnnotation(joinPoint,
        ProbabilisticEndpointDelaySimulation.class);
    var delay = createDelayFromAnnotation(annotation);
    delayResponse(delay);

    return joinPoint.proceed();
  }

  private void delayResponse(Delay delay) {
    if (delayProperties.logDelays()) {
      log.info("Delaying API response for {}ms...", delay.getDelayInMs());
    }
    delay.delay();
  }

}
