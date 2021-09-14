package at.kocmana.testservice.commons.delay;

import at.kocmana.testservice.commons.delay.annotation.FixedEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.annotation.ProbabilisticEndpointDelaySimulation;
import at.kocmana.testservice.commons.delay.model.FixedDelay;
import at.kocmana.testservice.commons.delay.model.NormallyDistributedDelay;
import at.kocmana.testservice.commons.delay.model.ProbabilisticDelay;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DelayFactory {

  public static FixedDelay createDelayFromAnnotation(FixedEndpointDelaySimulation aspect) {
    return new FixedDelay(aspect.delayInMs());
  }

  public static NormallyDistributedDelay createDelayFromAnnotation(NormallyDistributedEndpointDelaySimulation aspect) {
    return new NormallyDistributedDelay(aspect.mean(), aspect.standardDeviation());
  }

  public static ProbabilisticDelay createDelayFromAnnotation(ProbabilisticEndpointDelaySimulation aspect) {
    return new ProbabilisticDelay(aspect.probability(), aspect.delayInMs());
  }

}
