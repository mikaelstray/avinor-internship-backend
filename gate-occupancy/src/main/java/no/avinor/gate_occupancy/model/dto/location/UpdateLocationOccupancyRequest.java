package no.avinor.gate_occupancy.model.dto.location;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateLocationOccupancyRequest(
        @NotNull @Positive Integer locationId,

        @NotNull(message = "New pax is required")
        @Positive(message = "Amount must be positive")
        Integer newPax
) {}
