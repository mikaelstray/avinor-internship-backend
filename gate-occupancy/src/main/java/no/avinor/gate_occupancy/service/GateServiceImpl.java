package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.GateOccupancyDTO;
import no.avinor.gate_occupancy.model.GateOccupancy;
import no.avinor.gate_occupancy.repository.GateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    public GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    @Override
    public GateOccupancyDTO getCapacityForGate(int gateId) {
        GateOccupancy gate = gateRepository.findByGateId(gateId)
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        return new GateOccupancyDTO(
                gate.getGateId(),
                "Gate " + gate.getGateId(),
                gate.getFreeSeats(),
                gate.getStatus()
        );
    }

    @Override
    public List<GateOccupancyDTO> getAllGateCapacities() {
        return gateRepository.findAll().stream()
                .map(gate -> new GateOccupancyDTO(
                        gate.getGateId(),
                        "Gate " + gate.getGateId(),
                        gate.getFreeSeats(),
                        gate.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
