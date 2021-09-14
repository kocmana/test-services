package at.kocmana.testservices.customerservice.customerinformation.model.domain;

public enum Gender {
  MALE, FEMALE;

  @Override
  public String toString(){
    return name().toLowerCase();
  }
}
