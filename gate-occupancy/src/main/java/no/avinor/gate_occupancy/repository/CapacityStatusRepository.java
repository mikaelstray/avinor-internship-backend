package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.CapacityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for accessing crowdiness status information for an area.
 */
public interface CapacityStatusRepository extends JpaRepository<CapacityStatus,Long> {

}
