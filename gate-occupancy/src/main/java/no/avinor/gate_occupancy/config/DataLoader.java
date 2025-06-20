package no.avinor.gate_occupancy.config;

import jakarta.annotation.PostConstruct;
import no.avinor.gate_occupancy.model.Airport;
import no.avinor.gate_occupancy.model.Area;
import no.avinor.gate_occupancy.model.CapacityStatus;
import no.avinor.gate_occupancy.model.Crowdiness;
import no.avinor.gate_occupancy.repository.AreaRepository;
import no.avinor.gate_occupancy.repository.CapacityStatusRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DataLoader implements AreaRepository, CapacityStatusRepository {

    private final Map<Long, Area> areaStore = new HashMap<>();
    private final Map<Long, CapacityStatus> capacityStore = new HashMap<>();

    @PostConstruct
    public void loadData() {
        Airport airport = new Airport(1L, "Oslo Gardermoen");

        Area gateArea = new Area(101L, "Gate A5", airport.getName(), "Gate" );
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
