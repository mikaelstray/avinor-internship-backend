package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing area (e.g. gate, lounge) data.
 */
public interface AreaRepository extends JpaRepository<Zone,Long> {

}
