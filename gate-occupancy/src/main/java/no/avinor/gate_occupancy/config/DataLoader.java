package no.avinor.gate_occupancy.config;

import jakarta.annotation.PostConstruct;
import no.avinor.gate_occupancy.model.*;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataLoader implements AreaRepository, CapacityStatusRepository {

    private final Map<Long, Area> areaStore = new HashMap<>();
    private final Map<Long, CapacityStatus> capacityStore = new HashMap<>();

    @PostConstruct
    public void loadData() {
        // Opprett flyplass og terminal
        Airport airport = new Airport("OSL", "Oslo Gardermoen");
        Terminal terminal = new Terminal(1L, "Terminal 1", airport);

        // Opprett plasser
        Place gateA5 = new Place(123L, "Gate A5", 40);
        Place restaurant = new Place(456L, "Burger King", 20);

        Set<Place> places = new HashSet<>();
        places.add(gateA5);
        places.add(restaurant);

        // Opprett område
        Area gateArea = new Area(101L, "Departure Zone A", terminal, places);
        gateArea.setCapacity(100);
        gateArea.setSize(200.0);

        // Sett area i CapacityStatus
        CapacityStatus status = new CapacityStatus(1L, Crowdiness.LOW, LocalDateTime.now(), gateArea);

        // Lagre i minne-baserte "repoer"
        areaStore.put(gateArea.getId(), gateArea);
        capacityStore.put(gateArea.getId(), status);
    }

    @Override
    public Optional<Area> findById(Long id) {
        return Optional.ofNullable(areaStore.get(id));
    }

    @Override
    public Optional<CapacityStatus> findLatestByAreaId(Long areaId) {
        return Optional.ofNullable(capacityStore.get(areaId));
    }
}
