package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.AreaDTO;
import no.avinor.gate_occupancy.model.Area;
import no.avinor.gate_occupancy.model.CapacityStatus;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Service;

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
        // Get the area (e.g., a gate)
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));

        // Get the latest status for the area
        CapacityStatus status = capacityStatusRepository.findLatestByAreaId(areaId)
                .orElse(null);

        // Extract crowdiness value if status exists
        String crowdiness = (status != null) ? status.getCrowdiness() : "UNKNOWN";

        // Map model objects into a DTO for the frontend
        return new AreaDTO(
                area.getName(),
                area.getAirportName(),
                area.getAreaTypeName(),
                crowdiness
        );
    }
}
