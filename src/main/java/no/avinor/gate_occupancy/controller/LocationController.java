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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        logger.info("Controller: updating pax with location id {}, new pax: {}", id, request.newPax());
        LocationOccupancyStatus response = liveMapper.toDto(locationService.updatePax(id, request));
        logger.info("Controller: pax successfully updated");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationLiteResponse> getLocation(
            @PathVariable Long id
    ) {
        logger.info("Controller: Getting lite location dto with id: {} ", id);
        LocationLiteResponse response = locationMapper.toLiteResponse(locationService.getLocationById(id));
        logger.info("Controller: Location successfully retrieved");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/occupancy")
    public ResponseEntity<LocationOccupancyStatus> getLiveStatus(
            @PathVariable Long id
    ) {
        logger.info("Getting live status for location with id: {}", id);
        LocationOccupancyStatus response = liveMapper.toDto(locationService.getLocationLiveStatus(id));
        logger.info("Successfully retrieved live status");
        return ResponseEntity.ok(response);
    }
}
