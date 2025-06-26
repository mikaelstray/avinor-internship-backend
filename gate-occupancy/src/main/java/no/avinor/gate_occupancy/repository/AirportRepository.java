package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing airport data.
 * Useful for resolving airport names or verifying existence.
 */
public interface AirportRepository extends JpaRepository<Airport,Long> {

}
