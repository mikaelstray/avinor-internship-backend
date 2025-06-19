package no.avinor.gate_occupancy.config;

import no.avinor.gate_occupancy.model.GateOccupancy;
import no.avinor.gate_occupancy.model.enums.CapacityStatus;
import no.avinor.gate_occupancy.repository.GateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final GateRepository gateRepository;

    public DataLoader(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    @Override
    public void run(String... args) {
        GateOccupancy gate = new GateOccupancy();
        gate.setGateId(25);
        gate.setTotalSeats(100);
        gate.setFreeSeats(75);
        gate.setStatus(CapacityStatus.LOW);
        gateRepository.save(gate);
    }
}
