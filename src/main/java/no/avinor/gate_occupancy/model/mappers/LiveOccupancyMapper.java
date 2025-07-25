package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.occupancyStatus.LocationOccupancyStatus;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.stereotype.Component;

@Component
public class LiveOccupancyMapper {

    public LocationOccupancyStatus toDto(LocationLiveOccupancy liveOccupancy) {
        if (liveOccupancy == null) return null;
        return new LocationOccupancyStatus(
                liveOccupancy.getId(),
                liveOccupancy.getPax(),
                liveOccupancy.getUpdatedTime(),
                liveOccupancy.getLocation().getId()
        );
    }

}
