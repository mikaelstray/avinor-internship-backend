package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LocationOccupancyHistoryRepository extends JpaRepository<LocationOccupancyHistory, Long> {
    void deleteByCreatedAtBefore(LocalDateTime createdAtBefore);
}