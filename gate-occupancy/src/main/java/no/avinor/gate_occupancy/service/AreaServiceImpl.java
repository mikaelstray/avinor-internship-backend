/*package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.model.dto.AreaDTO;
import no.avinor.gate_occupancy.model.entities.Zone;
import no.avinor.gate_occupancy.model.entities.Location;
import no.avinor.gate_occupancy.model.entities.CapacityStatus;
import no.avinor.gate_occupancy.model.entities.Crowdiness;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final CapacityStatusRepository capacityStatusRepository;


    public AreaServiceImpl(AreaRepository areaRepository, CapacityStatusRepository capacityStatusRepository) {
        this.areaRepository = areaRepository;
        this.capacityStatusRepository = capacityStatusRepository;
    }


    @Override
    public AreaDTO getAreaStatus(Long areaId) {
        // Hent området
        Zone zone = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));

        // Hent crowdiness-status
        CapacityStatus status = capacityStatusRepository.findLatestByAreaId(areaId)
                .orElse(null);

        Crowdiness crowdiness = (status != null) ? status.getCrowdiness() : Crowdiness.UNKNOWN;

        // Konverter places til liste av navn
        List<String> places = zone.getPlaces().stream()
                .map(Location::getName)
                .collect(Collectors.toList());

        // Bygg og returner DTO
        return new AreaDTO(
                zone.getName(),
                zone.getTerminal().getAirport().getName(),
                zone.getTerminal().getName(),
                places,
                crowdiness
        );
    }}
*/