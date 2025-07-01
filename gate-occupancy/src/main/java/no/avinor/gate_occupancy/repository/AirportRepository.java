package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {

}
