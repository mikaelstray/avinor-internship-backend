package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Override
    boolean existsById(Long id);
    
    Optional<Location> findByName(String name);

    boolean existsByNameAndAndTerminal_Airport_Iata(String name, String terminalAirportIata);

    List<Location> findAllByTerminal_Id(Long terminalId);

    List<Location> findByTerminal_Airport_Iata(String terminalAirportIata);
}
