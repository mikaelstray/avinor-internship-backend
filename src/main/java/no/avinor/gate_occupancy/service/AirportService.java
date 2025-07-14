package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdateOccupancyRequest;
import no.avinor.gate_occupancy.model.entities.Airport;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import no.avinor.gate_occupancy.model.entities.Terminal;
import no.avinor.gate_occupancy.repository.AirportRepository;
import no.avinor.gate_occupancy.repository.LocationLiveOccupancyRepository;
import no.avinor.gate_occupancy.repository.LocationOccupancyHistoryRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import no.avinor.gate_occupancy.repository.TerminalRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AirportService {

    private final AirportRepository airportRepository;
    private final TerminalRepository terminalRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public List<Terminal> getTerminalsByAirportIata(String iata) {
        return terminalRepository.findByAirport_Iata(iata);
    }
}
