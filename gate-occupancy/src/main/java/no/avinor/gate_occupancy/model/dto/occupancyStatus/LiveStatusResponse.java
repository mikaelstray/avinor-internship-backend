package no.avinor.gate_occupancy.model.dto.occupancyStatus;

import java.time.LocalDateTime;

public record LiveStatusResponse(
        Long id,
        Integer pax,
        LocalDateTime updatedAt
) {
}
