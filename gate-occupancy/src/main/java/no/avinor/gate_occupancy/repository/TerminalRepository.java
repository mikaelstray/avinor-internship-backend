package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.entities.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    Optional<Terminal> findByName(String name);
    boolean existsByName(String name);
}
