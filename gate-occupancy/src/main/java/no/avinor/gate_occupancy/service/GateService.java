package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.GateOccupancyDTO;

import java.util.List;

public interface GateService {
    GateOccupancyDTO getCapacityForGate(int gateId);
    List<GateOccupancyDTO> getAllGateCapacities();
}
