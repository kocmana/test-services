package at.kocmana.testservice.commons.delay.interceptor;

import at.kocmana.testservice.commons.delay.config.DelayProperties;
import at.kocmana.testservice.commons.delay.model.NormallyDistributedDelay;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class NormallyDistributedServiceDelayInterceptor implements HandlerInterceptor {

  private final NormallyDistributedDelay delay;
  private final DelayProperties delayProperties;

  @Autowired
  public NormallyDistributedServiceDelayInterceptor(
      DelayProperties delayProperties) {
    this.delayProperties = delayProperties;
    this.delay = new NormallyDistributedDelay(
        delayProperties.serviceDelayMean(), delayProperties.serviceDelayStandardDeviation());
  }

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                           final Object handler) {
    delayService();
    return true;
  }

  private void delayService() {
    if (delayProperties.logDelays()) {
      log.info("Simulating RTT: Delaying service call for {}ms...", delay.getDelayInMs());
      delay.delay();
    }
  }

}
