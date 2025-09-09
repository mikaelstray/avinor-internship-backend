package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    Optional<Terminal> findByNameAndAirport_Iata(String name, String airportIata);
    boolean existsByNameAndAirport_Iata(String name, String airportIata);

    @Query("""
        SELECT DISTINCT t FROM Terminal t
        LEFT JOIN FETCH t.locations l
        WHERE t.airport.iata = :iata
        AND (l.type = no.avinor.gate_occupancy.model.entities.LocationType.GATE OR l IS NULL)
        ORDER BY t.name
    """)
    List<Terminal> findTerminalsWithGatesByAirportIata(@Param("iata") String iata);
}
