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

@RestControllerAdvice
public class GlobalExceptionHandler {

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

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDetail> handleIllegalArgumentException(@NonNull IllegalArgumentException e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.BAD_REQUEST, e, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetail> handleGenericException(@NonNull Exception e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
  }

  @ExceptionHandler(AppEntityNotFoundException.class)
  public ResponseEntity<ErrorDetail> handleAppEntityNotFoundException(@NonNull AppEntityNotFoundException e, WebRequest request) {
    return createErrorResponseEntity(e.getErrorMessage(), e, request);
  }

  @ExceptionHandler(EntityOperationException.class)
  public ResponseEntity<ErrorDetail> handleEntityOperationException(@NonNull EntityOperationException e, WebRequest request) {
    return createErrorResponseEntity(e.getErrorMessage(), e, request);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorDetail> handleIllegalStateException(@NonNull IllegalStateException e, WebRequest request) {
    return createErrorResponseEntity(HttpStatus.CONFLICT, e, request);
  }
}
