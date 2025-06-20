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
        //lager objektene som testes
        Airport airport = new Airport(1L, "Oslo Gardermoen");
        Terminal terminal = new Terminal(1L, "Terminal 1", airport);
        AreaType gateA5 = new AreaType(1L, "Gate A5");
        AreaType restaurant = new AreaType(2L, "Burger King");
        Set<AreaType> areaTypes = new HashSet<>();
        areaTypes.add(gateA5);
        areaTypes.add(restaurant);

        Area gateArea = new Area(101L, "Departure Zone A", airport, terminal, areaTypes);
        areaStore.put(gateArea.getId(), gateArea);

        CapacityStatus status = new CapacityStatus(1L, Crowdiness.LOW, LocalDateTime.now(), gateArea.getId());
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
