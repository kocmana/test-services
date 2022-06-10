package at.kocmana.testservice.commons.responses;

import at.kocmana.testservice.commons.requestid.RequestIdConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.slf4j.MDC;

@Getter
public class ErrorResponse {

  @JsonInclude(Include.NON_NULL)
  private String uuid;
  private String errorMessage;

  protected ErrorResponse(String errorMessage) {
    this.uuid = retrieveRequestUuid();
    this.errorMessage = errorMessage;
  }

  public static ErrorResponse withMessage(String errorMessage) {
    return new ErrorResponse(errorMessage);
  }

  private String retrieveRequestUuid() {
    return MDC.get(RequestIdConstants.REQUEST_ID_KEY);
  }
}
