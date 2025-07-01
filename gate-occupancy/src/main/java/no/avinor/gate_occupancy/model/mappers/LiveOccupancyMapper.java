package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.occupancyStatus.LiveStatusResponse;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.stereotype.Component;

@Component
public class LiveOccupancyMapper {

    public LiveStatusResponse toDto(LocationLiveOccupancy liveOccupancy) {
        if (liveOccupancy == null) return null;
        return new LiveStatusResponse(
                liveOccupancy.getId(),
                liveOccupancy.getPax(),
                liveOccupancy.getUpdatedTime()
        );
    }

}
