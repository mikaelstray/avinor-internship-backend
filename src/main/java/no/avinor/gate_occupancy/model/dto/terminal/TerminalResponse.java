package no.avinor.gate_occupancy.model.dto.terminal;

import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;

import java.util.List;

public record TerminalResponse(
        Long id,
        String name,
        List<LocationLiteResponse> locations
) {}
