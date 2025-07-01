package no.avinor.gate_occupancy.exception.customExceptions;

import lombok.Getter;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;

/**
 * Exception thrown when an expected entity is not found in the application.
 * <p>
 * Used to indicate that a requested resource (entity) could not be located in the database.
 * Contains a {@link CustomErrorMessage} providing detailed error information.
 * </p>
 */
@Getter
public class AppEntityNotFoundException extends RuntimeException {

  /**
   * Custom error message containing details about the exception.
   */
  private final CustomErrorMessage errorMessage;

  /**
   * Constructs a new {@code AppEntityNotFoundException} with the specified error message.
   *
   * @param errorMessage the detailed custom error message
   */
  public AppEntityNotFoundException(CustomErrorMessage errorMessage) {
    super(errorMessage.getMessage());
    this.errorMessage = errorMessage;
  }
}
