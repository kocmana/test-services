package at.kocmana.testservice.commons.delay.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedDelay implements Delay {

  private final int delayInMs;

  @Override
  public int getDelayInMs() {
    return delayInMs;
  }

}
