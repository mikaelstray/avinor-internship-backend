package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationLiveOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationLiveOccupancyRepository extends JpaRepository<LocationLiveOccupancy,Long> {
    Optional<LocationLiveOccupancy> findByLocation_Id(Long locationId);
}
