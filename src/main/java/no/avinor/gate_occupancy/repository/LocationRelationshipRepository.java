package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.LocationRelationship;
import no.avinor.gate_occupancy.model.entities.LocationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRelationshipRepository extends JpaRepository<LocationRelationship, Long> {
    Page<LocationRelationship> findAllBySourceLocation_IdAndTargetLocation_Type(
            Long sourceLocationId,
            LocationType type,
            Pageable pageable);

    List<LocationRelationship> findAllBySourceLocation_IdAndTargetLocation_Type(
            Long sourceLocationId,
            LocationType type,
            Sort sort
    );
}