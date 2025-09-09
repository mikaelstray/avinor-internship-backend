package no.avinor.gate_occupancy.exception.customExceptions;

import lombok.Getter;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;

@Getter
public class AppEntityNotFoundException extends RuntimeException {

  private final CustomErrorMessage errorMessage;

  public AppEntityNotFoundException(CustomErrorMessage errorMessage) {
    super(errorMessage.getMessage());
    this.errorMessage = errorMessage;
  }
}
