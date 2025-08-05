package no.avinor.gate_occupancy.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetail {

  private LocalDateTime timestamp;

  private int status;

  private String exceptionType;

  private String httpStatusMessage;

  private String message;

  private String path;

  public ErrorDetail(
          LocalDateTime timestamp,
          int status,
          String httpStatusMessage,
          String exceptionType,
          String message,
          String path
  ) {
    this.timestamp = timestamp;
    this.status = status;
    this.httpStatusMessage = httpStatusMessage;
    this.exceptionType = exceptionType;
    this.message = message;
    this.path = path;
  }
}
