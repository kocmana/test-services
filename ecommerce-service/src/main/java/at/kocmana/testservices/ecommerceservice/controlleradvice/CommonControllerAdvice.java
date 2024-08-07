package at.kocmana.testservices.ecommerceservice.controlleradvice;

import at.kocmana.testservice.commons.responses.ErrorResponse;
import at.kocmana.testservice.commons.responses.ValidationErrorResponse;
import at.kocmana.testservices.ecommerceservice.price.model.PriceNotFoundException;
import at.kocmana.testservices.ecommerceservice.purchase.model.PurchaseNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String GENERIC_ERROR_MESSAGE = "An error has occurred.";

  @ExceptionHandler({PurchaseNotFoundException.class, PriceNotFoundException.class})
  ResponseEntity<ErrorResponse> handlePriceNotFoundException(Exception exception) {
    log.warn(exception.getMessage());

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
    log.warn(exception.getMessage());

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.withMessage(exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleInvalidInput(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request) {
    log.warn("Invalid input for request {} {}.", request.getMethod(), request.getRequestURI());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(ValidationErrorResponse.forException(ex));
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
    log.error("Caught unknown exception: {}, {}", exception.getMessage(), exception.getStackTrace());

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorResponse.withMessage(GENERIC_ERROR_MESSAGE));
  }

}