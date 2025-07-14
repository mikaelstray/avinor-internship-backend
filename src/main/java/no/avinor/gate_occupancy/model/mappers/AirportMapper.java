package no.avinor.gate_occupancy.model.mappers;

import no.avinor.gate_occupancy.model.dto.airport.AirportLiteResponse;
import no.avinor.gate_occupancy.model.entities.Airport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AirportMapper {

    public AirportLiteResponse toLiteResponse(Airport airport) {
        if (airport == null) {
            return null;
        }

        return new AirportLiteResponse(
                airport.getId(),
                airport.getIata(),
                airport.getName()
        );
    }

    public List<AirportLiteResponse> toLiteResponseList(List<Airport> airports) {
        if (airports == null || airports.isEmpty()) {
            return Collections.emptyList();
        }

        return airports.stream()
                .map(this::toLiteResponse)
                .toList();
    }
}
