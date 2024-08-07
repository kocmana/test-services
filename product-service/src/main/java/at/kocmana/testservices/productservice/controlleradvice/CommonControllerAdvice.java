package at.kocmana.testservices.productservice.controlleradvice;

import at.kocmana.testservice.commons.responses.ErrorResponse;
import at.kocmana.testservice.commons.responses.ValidationErrorResponse;
import at.kocmana.testservices.productservice.productinformation.model.ProductInformationNotFoundException;
import at.kocmana.testservices.productservice.productreview.model.ProductReviewNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String GENERIC_ERROR_MESSAGE = "An error has occured.";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(ValidationErrorResponse.forException(ex));
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<ErrorResponse> handleValidationException(final ValidationException ex) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(ErrorResponse.withMessage(ex.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  ResponseEntity<ErrorResponse> handleMessageNotReadableException(final HttpMessageNotReadableException ex) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(ErrorResponse.withMessage(ex.getMessage()));
  }

  @ExceptionHandler({ProductInformationNotFoundException.class, ProductReviewNotFoundException.class})
  ResponseEntity<ErrorResponse> handleProductInformationNotFoundException(
      final ProductInformationNotFoundException ex) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(NOT_FOUND)
        .body(ErrorResponse.withMessage(ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleGenericException(final Exception ex) {
    log.warn("Uncaught exception has occurred: {}", ex.getMessage(), ex);

    return ResponseEntity
        .status(INTERNAL_SERVER_ERROR)
        .body(ErrorResponse.withMessage(GENERIC_ERROR_MESSAGE));
  }

}
