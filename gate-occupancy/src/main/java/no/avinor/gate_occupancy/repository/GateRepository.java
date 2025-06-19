package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.GateOccupancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GateRepository extends JpaRepository<GateOccupancy, Integer> {
    Optional<GateOccupancy> findByGateId(int gateId);
}
