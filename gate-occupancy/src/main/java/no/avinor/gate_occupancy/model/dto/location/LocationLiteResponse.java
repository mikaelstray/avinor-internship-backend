package no.avinor.gate_occupancy.model.dto.location;

public record LocationLiteResponse(
        Long id,
        String name,
        Integer capacity,
        String type //TODO: entitygraph with zone for zone name
) {}