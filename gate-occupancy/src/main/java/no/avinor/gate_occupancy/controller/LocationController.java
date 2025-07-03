package no.avinor.gate_occupancy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.LocationOccupancyStatus;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdateOccupancyRequest;
import no.avinor.gate_occupancy.model.mappers.LiveOccupancyMapper;
import no.avinor.gate_occupancy.model.mappers.LocationMapper;
import no.avinor.gate_occupancy.service.LocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Validated

public class LocationController {
    private static final Logger logger = LogManager.getLogger(LocationController.class);
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final LiveOccupancyMapper liveMapper;

    @PatchMapping("/{id}/pax")
    public ResponseEntity<LocationOccupancyStatus> updatePax(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOccupancyRequest request
            ) {
        LocationOccupancyStatus response = liveMapper.toDto(locationService.updatePax(id, request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationLiteResponse> getLocation(
            @PathVariable Long id
    ) {
        LocationLiteResponse response = locationMapper.toLiteResponse(locationService.getLocationById(id));
        return ResponseEntity.ok(response);
    }
}
