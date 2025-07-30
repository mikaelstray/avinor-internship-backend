package no.avinor.gate_occupancy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.avinor.gate_occupancy.model.dto.location.LocationLiteResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationRelationshipResponse;
import no.avinor.gate_occupancy.model.dto.location.LocationResponse;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.LocationOccupancyStatus;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdateOccupancyRequest;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import no.avinor.gate_occupancy.model.mappers.LiveOccupancyMapper;
import no.avinor.gate_occupancy.model.mappers.LocationMapper;
import no.avinor.gate_occupancy.model.mappers.LocationRelationMapper;
import no.avinor.gate_occupancy.service.LocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Validated

public class LocationController {
    private static final Logger logger = LogManager.getLogger(LocationController.class);
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final LiveOccupancyMapper liveMapper;
    private final LocationRelationMapper relationMapper;

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

    @GetMapping("/terminal/{terminalId}")
    public ResponseEntity<List<LocationLiteResponse>> getAllByTerminal(
            @PathVariable Long terminalId
    ) {
        List<LocationLiteResponse> responseList = locationMapper.toLiteResponseList(locationService.getAllByTerminal(terminalId));
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getLocation(
            @PathVariable Long id
    ) {
        logger.info("Controller: Getting location dto with id: {} ", id);
        LocationResponse response = locationMapper.toResponse(locationService.getLocationById(id));
        logger.info("Controller: Location successfully retrieved");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{locationId}/nearby/gates")
    public ResponseEntity<Page<LocationRelationshipResponse>> getNearbyGates(
            @PathVariable Long locationId,
            Pageable pageable
    ) {
        Page<LocationRelationship> relationshipPage = locationService.getNearbyGates(locationId, pageable);
        return ResponseEntity.ok(relationshipPage.map(relationMapper::toDto));
    }

    @GetMapping("/{locationId}/nearby/servings")
    public ResponseEntity<List<LocationRelationshipResponse>> getNearbyServings(
            @PathVariable Long locationId,
            Sort sort
    ) {
        List<LocationRelationshipResponse> response = relationMapper.toDtoList(locationService.getNearbyServings(locationId, sort));
        return ResponseEntity.ok(response);
    }
}
