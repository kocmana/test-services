package at.kocmana.testservice.commons.delay.interceptor;

import at.kocmana.testservice.commons.delay.annotation.FixedEndpointDelaySimulation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class FixedServiceDelayInterceptor implements HandlerInterceptor {

  @Override
  @FixedEndpointDelaySimulation(delayInMs = 100)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
