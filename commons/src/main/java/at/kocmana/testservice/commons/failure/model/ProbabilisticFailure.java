package at.kocmana.testservice.commons.failure.model;

import lombok.Value;

import java.util.Random;

@Value
public class ProbabilisticFailure {

  float probability;
  int httpStatus;
  Random random = new Random();

  public boolean shouldCallFail() {
    return (random.nextInt() * 100) > probability;
  }

}
