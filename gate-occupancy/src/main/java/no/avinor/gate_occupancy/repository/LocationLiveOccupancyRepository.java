package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationLiveOccupancyRepository extends JpaRepository<LocationLiveOccupancy,Long> {

}
