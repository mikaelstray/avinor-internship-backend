package no.avinor.gate_occupancy.config;

import jakarta.annotation.PostConstruct;
import no.avinor.gate_occupancy.model.entities.*;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataLoader implements AreaRepository, CapacityStatusRepository {

    private final Map<Long, Zone> areaStore = new HashMap<>();
    private final Map<Long, CapacityStatus> capacityStore = new HashMap<>();

    @PostConstruct
    public void loadData() {
        // Opprett flyplass og terminal
        Airport airport = new Airport("OSL", "Oslo Gardermoen");
        Terminal terminal = new Terminal(1L, "Terminal 1", airport);

        // Opprett plasser
        Location gateA5 = new Location(123L, "Gate A5", 40);
        Location restaurant = new Location(456L, "Burger King", 20);

        Set<Location> locations = new HashSet<>();
        locations.add(gateA5);
        locations.add(restaurant);

        // Opprett område
        Zone gateZone = new Zone(101L, "Departure Zone A", terminal, locations);
        gateZone.setCapacity(100);
        gateZone.setSize(200.0);

        // Sett area i CapacityStatus
        CapacityStatus status = new CapacityStatus(1L, Crowdiness.LOW, LocalDateTime.now(), gateZone);

        // Lagre i minne-baserte "repoer"
        areaStore.put(gateZone.getId(), gateZone);
        capacityStore.put(gateZone.getId(), status);
    }

    @Override
    public Optional<Zone> findById(Long id) {
        return Optional.ofNullable(areaStore.get(id));
    }

    @Override
    public Optional<CapacityStatus> findLatestByAreaId(Long areaId) {
        return Optional.ofNullable(capacityStore.get(areaId));
    }
}
