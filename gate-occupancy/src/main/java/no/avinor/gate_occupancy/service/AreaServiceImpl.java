package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.AreaDTO;
import no.avinor.gate_occupancy.model.Area;
import no.avinor.gate_occupancy.model.AreaType;
import no.avinor.gate_occupancy.model.CapacityStatus;
import no.avinor.gate_occupancy.model.Crowdiness;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of AreaService.
 * Responsible for combining data from multiple repositories to build a response for the client.
 */
@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final CapacityStatusRepository capacityStatusRepository;

    /**
     * Constructor-based dependency injection.
     * Spring will provide the correct implementations at runtime.
     */
    public AreaServiceImpl(AreaRepository areaRepository, CapacityStatusRepository capacityStatusRepository) {
        this.areaRepository = areaRepository;
        this.capacityStatusRepository = capacityStatusRepository;
    }

    /**
     * Fetches the area and its latest crowdiness data, and converts it to a DTO.
     *
     * @param areaId ID of the area
     * @return AreaDTO with area info and status
     * @throws RuntimeException if area is not found
     */
    @Override
    public AreaDTO getAreaStatus(Long areaId) {
        // Hent området
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));

        // Hent crowdiness-status
        CapacityStatus status = capacityStatusRepository.findLatestByAreaId(areaId)
                .orElse(null);

        Crowdiness crowdiness = (status != null) ? status.getCrowdiness() : Crowdiness.UNKNOWN;

        // Konverter areaTypes til liste av navn
        List<String> areaTypeNames = area.getAreaTypes().stream()
                .map(AreaType::getName)
                .collect(java.util.stream.Collectors.toList());

        // Bygg og returner DTO
        return new AreaDTO(
                area.getName(),
                area.getAirport().getName(),
                area.getTerminal().getName(),
                areaTypeNames,
                crowdiness
        );
    }

}
