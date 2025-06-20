package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.Airport;
import java.util.Optional;

/**
 * Interface for accessing airport data.
 * Useful for resolving airport names or verifying existence.
 */
public interface AirportRepository {

    /**
     * Find an airport by its unique ID.
     *
     * @param id airport identifier
     * @return Optional containing Airport if found
     */
    Optional<Airport> findById(Long id);
}
