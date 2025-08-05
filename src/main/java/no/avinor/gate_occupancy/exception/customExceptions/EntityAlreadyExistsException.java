package no.avinor.gate_occupancy.exception.customExceptions;

import lombok.Getter;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {

  private final CustomErrorMessage errorMessage;

  public EntityAlreadyExistsException(CustomErrorMessage errorMessage) {
    super(errorMessage.getMessage());
    this.errorMessage = errorMessage;
  }
}
