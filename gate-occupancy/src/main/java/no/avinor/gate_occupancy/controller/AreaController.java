package no.avinor.gate_occupancy.controller;

import no.avinor.gate_occupancy.dto.AreaDTO;
import no.avinor.gate_occupancy.service.AreaService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that handles HTTP requests related to airport areas.
 * Exposes endpoints for retrieving real-time crowdiness status of airport zones (e.g., gates).
 */
@RestController
@RequestMapping("/api/areas") // Base path for all area-related endpoints
public class AreaController {

    private final AreaService areaService;

    /**
     * Constructor injection for the AreaService dependency.
     * Spring automatically injects the appropriate implementation.
     */
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    /**
     * GET endpoint that returns crowdiness status for a specific area by ID.
     *
     * Example: GET /api/areas/101/status
     *
     * @param id Area ID to look up
     * @return DTO containing area and status info
     */
    @GetMapping("/{id}/status")
    public AreaDTO getAreaStatus(@PathVariable Long id) {
        return areaService.getAreaStatus(id);
    }
}
