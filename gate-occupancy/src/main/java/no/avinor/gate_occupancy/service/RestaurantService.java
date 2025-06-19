package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> getNearbyRestaurants(int gateId);
}
