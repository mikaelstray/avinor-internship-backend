package no.avinor.gate_occupancy.model.mappers;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.entities.Location;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
public class LocationMapper {

    private final TerminalMapper terminalMapper;

    public LocationResponse toResponse(Location location) {
        if (location == null) {
            return null;
        }

        TerminalLiteResponse terminalResponse = terminalMapper.toLiteResponse(location.getTerminal());

        List<LocationLiteResponse> nearbyLocationsResponse = location.getNearbyLocations() != null
                ? location.getNearbyLocations().stream()
                .map(this::toLiteResponse)
                .toList()
                : Collections.emptyList();

        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getCapacity(),
                location.getType() != null ? location.getType().name() : null,
                terminalResponse,
                nearbyLocationsResponse
        );
    }

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