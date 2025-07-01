package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationOccupancyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LocationOccupancyHistoryRepository extends JpaRepository<LocationOccupancyHistory, Long> {
    void deleteByCreatedAtBefore(LocalDateTime createdAtBefore);
}