package at.kocmana.testservice.commons.requestid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestIdConstants {

  public static final String REQUEST_ID_KEY = "request-id";
  public static final String REQUEST_ID_HEADER_NAME = "X-Request-ID";

}
