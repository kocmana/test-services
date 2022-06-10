package at.kocmana.testservice.commons.failure.aspect;

import at.kocmana.testservice.commons.failure.annotation.ProbabilisticFailureSimulation;
import at.kocmana.testservice.commons.failure.model.ProbabilisticFailure;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static at.kocmana.testservice.commons.util.AnnotationUtils.extractAnnotation;

@Aspect
@Component
@Slf4j
public class FailureAspect {

  @Around("@annotation(at.kocmana.testservice.commons.failure.annotation.ProbabilisticFailureSimulation)")
  public Object failWithProbability(ProceedingJoinPoint joinPoint) throws Throwable {
    var annotation = extractAnnotation(joinPoint,
        ProbabilisticFailureSimulation.class);
    var probabilisticFailure = new ProbabilisticFailure(annotation.probability(),
        annotation.errorCode());

    if (probabilisticFailure.shouldCallFail()) {
      log.warn("Call failed with error code {} due to simulated probabilistic error.",
          probabilisticFailure.getHttpStatus());
      return ResponseEntity.status(probabilisticFailure.getHttpStatus()).build();
    }

    return joinPoint.proceed();
  }

}
