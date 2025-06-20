package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.Area;
import java.util.Optional;

/**
 * Interface for accessing area (e.g. gate, lounge) data.
 */
public interface AreaRepository {

    /**
     * Find a specific area by ID.
     *
     * @param id area identifier
     * @return Optional containing Area if found
     */
    Optional<Area> findById(Long id);
}
