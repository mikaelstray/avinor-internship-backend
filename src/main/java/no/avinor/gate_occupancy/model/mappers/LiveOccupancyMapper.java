package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.occupancyStatus.LocationOccupancyStatus;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.stereotype.Component;

@Component
public class LiveOccupancyMapper {

    public LocationOccupancyStatus toDto(LocationLiveOccupancy liveOccupancy) {
        return new LocationOccupancyStatus(
                true,
                liveOccupancy.getId(),
                liveOccupancy.getPax(),
                liveOccupancy.getUpdatedTime(),
                liveOccupancy.getLocation().getId()
        );
    }

    public LocationOccupancyStatus toUnavailableDto(Long locationId) {
        return new LocationOccupancyStatus(
                false,
                null,
                null,
                null,
                locationId
        );
    }
}
