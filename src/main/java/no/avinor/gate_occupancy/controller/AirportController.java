package no.avinor.gate_occupancy.controller;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.airport.AirportLiteResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.mappers.AirportMapper;
import no.avinor.gate_occupancy.model.mappers.LocationMapper;
import no.avinor.gate_occupancy.model.mappers.TerminalMapper;
import no.avinor.gate_occupancy.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/airports")
@RestController
@Validated
@RequiredArgsConstructor

public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;
    private final TerminalMapper terminalMapper;
    private final LocationMapper locationMapper;

    @GetMapping
    public ResponseEntity<List<AirportLiteResponse>> getAllLite() {
        List<AirportLiteResponse> response = airportMapper.toLiteResponseList(airportService.getAllAirports());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{iata}/terminals")
    public ResponseEntity<List<TerminalLiteResponse>> getTerminalsByAirportIata(
            @PathVariable String iata
    ) {
        List<TerminalLiteResponse> response = terminalMapper.toLiteResponseList(airportService.getTerminalsByAirportIata(iata));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{iata}/locations")
    public ResponseEntity<List<LocationLiteResponse>> getLocationsByAirportIata(
            @PathVariable String iata
    ) {
        List<LocationLiteResponse> response = locationMapper.toLiteResponseList(airportService.getLocationsByAirportIata(iata));
        return ResponseEntity.ok(response);
    }

}
