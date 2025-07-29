package no.avinor.gate_occupancy.model.dto.location;

public record LocationRelationshipResponse(
        Long id,
        LocationLiteResponse targetLocation,
        Integer walkingTimeInMinute
) {}