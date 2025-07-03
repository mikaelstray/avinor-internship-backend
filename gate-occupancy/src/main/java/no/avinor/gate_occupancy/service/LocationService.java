package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdateOccupancyRequest;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import no.avinor.gate_occupancy.repository.LocationLiveOccupancyRepository;
import no.avinor.gate_occupancy.repository.LocationOccupancyHistoryRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationLiveOccupancyRepository liveRepository;
    private final LocationOccupancyHistoryRepository historyRepository;
    private final PaxNotifierService paxNotifierService;

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.LOCATION_NOT_FOUND));
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

        paxNotifierService.notifyPaxUpdate(liveStatus); //TODO: convert to dto
        return liveStatus;
    }
}
