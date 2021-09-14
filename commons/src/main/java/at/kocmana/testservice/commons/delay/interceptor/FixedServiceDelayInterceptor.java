package at.kocmana.testservice.commons.delay.interceptor;

import at.kocmana.testservice.commons.delay.annotation.FixedEndpointDelaySimulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class FixedServiceDelayInterceptor implements HandlerInterceptor {

  @Override
  @FixedEndpointDelaySimulation(delayInMs = 100)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
