package at.kocmana.testservice.commons.delay.interceptor;

import at.kocmana.testservice.commons.delay.annotation.ProbabilisticEndpointDelaySimulation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class ProbabilisticServiceDelayInterceptor implements HandlerInterceptor {

  @Override
  @ProbabilisticEndpointDelaySimulation(probability = 50, delayInMs = 100)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
