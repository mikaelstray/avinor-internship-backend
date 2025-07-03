package no.avinor.gate_occupancy.model.dto.location;

import no.avinor.gate_occupancy.model.dto.zone.ZoneLiteResponse;
import no.avinor.gate_occupancy.model.entities.LocationType;

public record LocationResponse(
        Long id,
        String name,
        Integer number,
        LocationType type,
        ZoneLiteResponse zone
) {}