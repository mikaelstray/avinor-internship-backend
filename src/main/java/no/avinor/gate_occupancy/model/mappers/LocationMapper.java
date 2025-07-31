package no.avinor.gate_occupancy.model.mappers;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationRelationshipResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.entities.Location;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class LocationMapper {

    private final LocationRelationMapper relationMapper;

    public LocationResponse toResponse(Location location) {
        if (location == null) {
            return null;
        }

        TerminalLiteResponse terminalResponse = new TerminalLiteResponse(
                    location.getTerminal().getId(),
                    location.getTerminal().getName()
            );


        List<LocationRelationshipResponse> nearbyLocationsResponse = location.getRelationships() != null
                ? location.getRelationships().stream()
                .map(relationMapper::toDto)
                .toList()
                : Collections.emptyList();

        return new LocationResponse(
                location.getId(),
                location.getName(),
                location.getCapacity(),
                location.getType() != null ? location.getType().name() : null,
                location.getImageUrl(),
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
                location.getType() != null ? location.getType().name() : null,
                location.getImageUrl()
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