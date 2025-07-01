package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
