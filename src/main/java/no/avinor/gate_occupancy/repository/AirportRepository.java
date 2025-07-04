package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> findByName(String name);
    boolean existsByName(String name);
}
