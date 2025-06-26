package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.model.dto.AreaDTO;

/**
 * Service interface for handling logic related to airport areas.
 * Defines operations available for clients (e.g. controllers).
 */
public interface AreaService {

    /**
     * Returns the status of an area (such as a gate), including its crowdiness level.
     *
     * @param areaId ID of the area
     * @return AreaDTO containing area information and latest crowdiness status
     */
    AreaDTO getAreaStatus(Long areaId);
}
