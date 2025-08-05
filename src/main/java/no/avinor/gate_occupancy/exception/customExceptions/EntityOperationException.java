package no.avinor.gate_occupancy.exception.customExceptions;

import lombok.Getter;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;

@Getter
public class EntityOperationException extends RuntimeException {

  private final CustomErrorMessage errorMessage;

  public EntityOperationException(CustomErrorMessage errorMessage) {
    super(errorMessage.getMessage());
    this.errorMessage = errorMessage;
  }
}
