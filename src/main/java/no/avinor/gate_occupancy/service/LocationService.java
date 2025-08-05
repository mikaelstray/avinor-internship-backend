package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdateOccupancyRequest;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import no.avinor.gate_occupancy.model.entities.LocationType;
import no.avinor.gate_occupancy.repository.LocationLiveOccupancyRepository;
import no.avinor.gate_occupancy.repository.LocationOccupancyHistoryRepository;
import no.avinor.gate_occupancy.repository.LocationRelationshipRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationLiveOccupancyRepository liveRepository;
    private final LocationOccupancyHistoryRepository historyRepository;
    private final LocationRelationshipRepository relationshipRepository;
    private final PaxNotifierService paxNotifierService;

    private static final Map<String, String> ALLOWED_SORT_FIELDS = Map.of(
            "walkingTime", "walkingTimeInMinutes",
            "name", "targetLocation.name"
    );

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.LOCATION_NOT_FOUND));
    }

    public Optional<LocationLiveOccupancy> getLocationLiveStatus(Long id) {
        return liveRepository.findByLocation_Id(id);

    }

    public List<Location> getAllByTerminal(Long terminalId) {
        return locationRepository.findAllByTerminal_Id(terminalId);
    }

    @Transactional
    public LocationLiveOccupancy updatePax(Long locationId, UpdateOccupancyRequest request) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.LOCATION_NOT_FOUND));

        LocationLiveOccupancy liveStatus = liveRepository.findByLocation_Id(locationId)
                .orElse(new LocationLiveOccupancy().setLocation(location));

        liveStatus.setPax(request.newPax());
        liveRepository.save(liveStatus);

        LocationOccupancyHistory history = new LocationOccupancyHistory()
                .setLocation(location)
                .setPax(request.newPax());
        historyRepository.save(history);

        paxNotifierService.notifyPaxUpdate(liveStatus);
        return liveStatus;
    }


    public Page<LocationRelationship> getNearbyGates(Long locationId, Pageable pageable) {
        Pageable validatedPageable = buildValidatedPageable(pageable);

        return relationshipRepository.findAllBySourceLocation_IdAndTargetLocation_Type(
                locationId,
                LocationType.GATE,
                validatedPageable
        );
    }

    public List<LocationRelationship> getNearbyServings(Long locationId, Sort sort) {
        Sort validatedSort = buildValidatedPageable(PageRequest.of(0, 999, sort)).getSort();

        return relationshipRepository.findAllBySourceLocation_IdAndTargetLocation_Type(
                locationId,
                LocationType.SERVING,
                validatedSort
        );
    }

    private Pageable buildValidatedPageable(Pageable originalPageable) {
        List<Sort.Order> validatedOrders = originalPageable.getSort().stream()
                .map(order -> {
                    String clientFieldName = order.getProperty();
                    String entityFieldName = ALLOWED_SORT_FIELDS.get(clientFieldName);

                    if (entityFieldName == null) {
                        throw new IllegalArgumentException("Sorting by '" + clientFieldName + "' is not allowed.");
                    }

                    return new Sort.Order(order.getDirection(), entityFieldName);
                })
                .toList();

        return PageRequest.of(
                originalPageable.getPageNumber(),
                originalPageable.getPageSize(),
                Sort.by(validatedOrders)
        );
    }
}