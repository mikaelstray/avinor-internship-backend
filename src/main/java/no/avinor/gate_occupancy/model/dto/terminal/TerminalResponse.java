package no.avinor.gate_occupancy.model.dto.terminal;

import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;

import java.util.List;
import java.util.Set;

public record TerminalResponse(
        Long id,
        String name,
        List<LocationLiteResponse> locations
) {}
