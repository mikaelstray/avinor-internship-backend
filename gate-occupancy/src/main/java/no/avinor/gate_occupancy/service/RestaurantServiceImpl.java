package no.avinor.gate_occupancy.service;

import no.avinor.gate_occupancy.dto.RestaurantDTO;
import no.avinor.gate_occupancy.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDTO> getNearbyRestaurants(int gateId) {
        return restaurantRepository.findByGateId(gateId).stream()
                .map(r -> new RestaurantDTO(r.getName(), r.getDistanceMeters(), r.isHasSeating()))
                .collect(Collectors.toList());
    }
}
