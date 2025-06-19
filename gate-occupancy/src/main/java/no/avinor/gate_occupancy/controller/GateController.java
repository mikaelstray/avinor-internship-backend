package no.avinor.gate_occupancy.controller;

import no.avinor.gate_occupancy.dto.GateOccupancyDTO;
import no.avinor.gate_occupancy.service.GateServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gates")
public class GateController {

    private final GateServiceImpl gateService;

    public GateController(GateServiceImpl gateService) {
        this.gateService = gateService;
    }

    @GetMapping("/{gateId}/capacity")
    public GateOccupancyDTO getCapacity(@PathVariable int gateId) {
        return gateService.getCapacityForGate(gateId);
    }

    @GetMapping
    public List<GateOccupancyDTO> getAll() {
        return gateService.getAllGateCapacities();
    }
}
