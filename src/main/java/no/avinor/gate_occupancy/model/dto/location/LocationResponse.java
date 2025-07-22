package no.avinor.gate_occupancy.model.dto.location;

import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.model.entities.Terminal;

import java.util.List;

public record LocationResponse(
        Long id,
        String name,
        Integer number,
        String type,
        TerminalLiteResponse terminal,
        List<LocationLiteResponse> nearbyLocations
) {}