package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRelationshipRepository extends JpaRepository<LocationRelationship, Long> {
    List<LocationRelationship> findAllBySourceLocation_Id(Long sourceLocationId);
}
