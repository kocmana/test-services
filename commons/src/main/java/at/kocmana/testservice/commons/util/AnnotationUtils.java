package at.kocmana.testservice.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnnotationUtils {

  public static <T extends Annotation> T extractAnnotation(ProceedingJoinPoint joinPoint,
      Class<T> annotationType) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();

    return findAnnotation(method, annotationType);
  }
}
