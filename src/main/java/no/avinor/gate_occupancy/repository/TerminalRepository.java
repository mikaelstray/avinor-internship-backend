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

    @Query(value = """
        SELECT t.*
        FROM terminal t
        JOIN airport a ON t.airport_id = a.id
        WHERE a.iata_code = :airportIata
        ORDER BY
            LEFT(t.name, PATINDEX('%[0-9]%', t.name + '0') - 1),
            TRY_CAST(SUBSTRING(t.name, PATINDEX('%[0-9]%', t.name + '0'), LEN(t.name)) AS INT)
    """, nativeQuery = true)
    List<Terminal> findByAirport_Iata_SortedNatural(@Param("airportIata") String airportIata);
}
