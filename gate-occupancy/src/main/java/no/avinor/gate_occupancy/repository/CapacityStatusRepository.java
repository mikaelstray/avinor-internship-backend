package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.CapacityStatus;
import java.util.Optional;

/**
 * Interface for accessing crowdiness status information for an area.
 */
public interface CapacityStatusRepository {

    /**
     * Get the latest known capacity status for a given area.
     *
     * @param areaId ID of the area
     * @return Optional containing most recent CapacityStatus
     */
    Optional<CapacityStatus> findLatestByAreaId(Long areaId);
}
