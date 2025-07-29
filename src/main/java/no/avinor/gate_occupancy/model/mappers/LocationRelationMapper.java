package no.avinor.gate_occupancy.model.mappers;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationRelationshipResponse;
import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationRelationMapper {

    public LocationRelationshipResponse toDto(LocationRelationship relationship) {
        if (relationship == null) {
            return null;
        }

        LocationLiteResponse locationLiteResponse = new LocationLiteResponse(
                relationship.getTargetLocation().getId(),
                relationship.getTargetLocation().getName(),
                relationship.getTargetLocation().getCapacity(),
                relationship.getTargetLocation().getType().name()
        );

        return new LocationRelationshipResponse(
                relationship.getId(),
                locationLiteResponse,
                relationship.getWalkingTimeInMinutes()
        );
    }

    public List<LocationRelationshipResponse> toDtoList(Collection<LocationRelationship> relationships) {
        if (relationships == null) {
            return Collections.emptyList();
        }

        return relationships.stream()
                .map(this::toDto)
                .toList();
    }

}