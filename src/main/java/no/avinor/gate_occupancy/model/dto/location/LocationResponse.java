package no.avinor.gate_occupancy.model.dto.location;

import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;

import java.util.List;

public record LocationResponse(
        Long id,
        String name,
        Integer capacity,
        String type,
        String imageUrl,
        TerminalLiteResponse terminal,
        List<LocationRelationshipResponse> nearbyLocations
) {}