package no.avinor.gate_occupancy.exception;

import lombok.NonNull;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.exception.customExceptions.EntityOperationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Global exception handler for handling various exceptions across the application.
 * <p>
 * Provides centralized exception handling using {@link RestControllerAdvice}.
 * Ensures that all errors are returned with a consistent structure.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Creates an error response entity for exceptions with a predefined {@link CustomErrorMessage}.
   *
   * @param errorMessage the predefined custom error message
   * @param e            the thrown exception
   * @param request      the current web request
   * @return a structured {@link ResponseEntity} containing {@link ErrorDetail}
   */
  private ResponseEntity<ErrorDetail> createErrorResponseEntity(CustomErrorMessage errorMessage, Exception e, WebRequest request) {
    ErrorDetail errorDetail = new ErrorDetail(
            LocalDateTime.now(),
            errorMessage.getStatus(),
            HttpStatus.valueOf(errorMessage.getStatus()).getReasonPhrase(),
            e.getClass().getName(),
            errorMessage.getMessage(),
            request.getDescription(false)
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<>(errorDetail, headers, HttpStatus.valueOf(errorMessage.getStatus()));
  }

  /**
   * Creates an error response entity for generic exceptions with dynamic {@link HttpStatus}.
   *
   * @param status  the HTTP status to be returned
   * @param e       the thrown exception
   * @param request the current web request
   * @return a structured {@link ResponseEntity} containing {@link ErrorDetail}
   */
  private ResponseEntity<ErrorDetail> createErrorResponseEntity(HttpStatus status, Exception e, WebRequest request) {
    ErrorDetail error = new ErrorDetail(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            e.getClass().getName(),
            e.getMessage(),
            request.getDescription(false)
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<>(error, headers, status);
  }

  /**
   * Handles {@link IllegalArgumentException} for invalid method arguments.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDetail> handleIllegalArgumentException(@NonNull IllegalArgumentException e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.BAD_REQUEST, e, request);
  }

  /**
   * Handles any unhandled exceptions.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetail> handleGenericException(@NonNull Exception e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
  }

  /**
   * Handles {@link AppEntityNotFoundException} when an entity cannot be found.
   */
  @ExceptionHandler(AppEntityNotFoundException.class)
  public ResponseEntity<ErrorDetail> handleAppEntityNotFoundException(@NonNull AppEntityNotFoundException e, WebRequest request) {
    return createErrorResponseEntity(e.getErrorMessage(), e, request);
  }

  /**
   * Handles {@link EntityOperationException} when a generic operation on an entity fails.
   */
  @ExceptionHandler(EntityOperationException.class)
  public ResponseEntity<ErrorDetail> handleEntityOperationException(@NonNull EntityOperationException e, WebRequest request) {
    return createErrorResponseEntity(e.getErrorMessage(), e, request);
  }

  /**
   * Handles {@link IllegalStateException} when an entity cannot be found.
   */
  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorDetail> handleIllegalStateException(@NonNull IllegalStateException e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.CONFLICT, e, request);
  }
}
