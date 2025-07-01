package no.avinor.gate_occupancy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.avinor.gate_occupancy.exception.CustomErrorMessage;
import no.avinor.gate_occupancy.exception.customExceptions.AppEntityNotFoundException;
import no.avinor.gate_occupancy.model.dto.occupancyStatus.UpdatePaxRequest;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import no.avinor.gate_occupancy.repository.LocationOccupancyHistoryRepository;
import no.avinor.gate_occupancy.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LocationService {

    LocationRepository locationRepository;
    LocationOccupancyHistoryRepository historyRepository;
    PaxNotifierService paxNotifierService;

    //decided to keep service methods business logic only. mapping to dto will be done in the controller,
    //to ensure reusable methods enabling possibility of different dtos etc.
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.LOCATION_NOT_FOUND));
    }

    @Transactional
    public LocationLiveOccupancy updatePax(Long locationId, UpdatePaxRequest request) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new AppEntityNotFoundException(CustomErrorMessage.LOCATION_NOT_FOUND));

        LocationLiveOccupancy liveStatus = location.getLiveStatus();
        if (liveStatus == null) {
            liveStatus = new LocationLiveOccupancy()
                    .setLocation(location);
            location.setLiveStatus(liveStatus);
        }

        liveStatus.setPax(request.newPax());
        locationRepository.save(location);

        LocationOccupancyHistory history = new LocationOccupancyHistory()
                .setLocation(location)
                .setPax(request.newPax());
        historyRepository.save(history);

        paxNotifierService.notifyPaxUpdate(liveStatus); //TODO: convert to dto
        return liveStatus;
    }
}
