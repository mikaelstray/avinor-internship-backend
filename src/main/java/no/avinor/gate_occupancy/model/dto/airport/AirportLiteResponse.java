package no.avinor.gate_occupancy.model.dto.airport;

public record AirportLiteResponse(
        Long id,
        String iata,
        String name
) {}