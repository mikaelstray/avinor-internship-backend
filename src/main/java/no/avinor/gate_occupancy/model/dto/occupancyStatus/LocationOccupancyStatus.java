package no.avinor.gate_occupancy.model.dto.occupancyStatus;

import java.time.LocalDateTime;

public record LocationOccupancyStatus(
        Long id,
        Integer pax,
        LocalDateTime updatedAt,
        Long locationId
) {
}
