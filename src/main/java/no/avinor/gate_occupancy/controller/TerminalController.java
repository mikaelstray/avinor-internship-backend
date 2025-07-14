package no.avinor.gate_occupancy.controller;

import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.airport.AirportLiteResponse;
import no.avinor.gate_occupancy.model.dto.terminal.TerminalLiteResponse;
import no.avinor.gate_occupancy.model.mappers.AirportMapper;
import no.avinor.gate_occupancy.model.mappers.TerminalMapper;
import no.avinor.gate_occupancy.service.AirportService;
import no.avinor.gate_occupancy.service.TerminalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/terminals")
@RestController
@Validated
@RequiredArgsConstructor

public class TerminalController {

    private final TerminalService terminalService;
    private final TerminalMapper terminalMapper;

    @GetMapping
    public ResponseEntity<List<TerminalLiteResponse>> getAllLite() {
        List<TerminalLiteResponse> response = terminalMapper.toLiteResponseList(terminalService.getAllTerminals());
        return ResponseEntity.ok(response);
    }


}
