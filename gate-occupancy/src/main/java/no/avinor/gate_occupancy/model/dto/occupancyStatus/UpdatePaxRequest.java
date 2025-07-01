package no.avinor.gate_occupancy.model.dto.occupancyStatus;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdatePaxRequest(
        @NotNull(message = "New pax is required")
        @PositiveOrZero(message = "Amount cannot be negative")
        Integer newPax
) {}
