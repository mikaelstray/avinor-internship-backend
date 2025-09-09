package no.avinor.gate_occupancy.model.dto.occupancyStatus;

import java.time.LocalDateTime;

public record LocationOccupancyStatus(
        boolean available,
        Long id,
        Integer pax,
        LocalDateTime updatedAt,
        Long locationId
) {
}
