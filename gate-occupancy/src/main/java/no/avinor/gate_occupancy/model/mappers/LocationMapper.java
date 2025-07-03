package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.entities.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationMapper {

    public LocationLiteResponse toLiteResponse(Location location) {
        if (location == null) {
            return null;
        }

        return new LocationLiteResponse(
                location.getId(),
                location.getName(),
                location.getCapacity(),
                location.getType() != null ? location.getType().name() : null
        );
    }

    public List<LocationLiteResponse> toLiteResponseList(List<Location> locations) {
        if (locations == null) {
            return List.of();
        }

        return locations.stream()
                .map(this::toLiteResponse)
                .toList();
    }
}