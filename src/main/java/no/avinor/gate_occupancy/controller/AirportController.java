package no.avinor.gate_occupancy.controller;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.airport.AirportLiteResponse;
import no.avinor.gate_occupancy.model.mappers.AirportMapper;
import no.avinor.gate_occupancy.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<AirportLiteResponse>> getAllLite() {
        List<AirportLiteResponse> response = airportMapper.toLiteResponseList(airportService.getAllAirports());
        return ResponseEntity.ok(response);
    }

}
