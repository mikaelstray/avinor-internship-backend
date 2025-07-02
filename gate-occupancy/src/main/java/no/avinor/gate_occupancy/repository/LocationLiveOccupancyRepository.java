package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationLiveOccupancyRepository extends JpaRepository<LocationLiveOccupancy,Long> {
    Optional<LocationLiveOccupancy> findByLocation_Id(Long locationId);
}
