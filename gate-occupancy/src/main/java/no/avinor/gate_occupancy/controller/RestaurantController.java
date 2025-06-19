package no.avinor.gate_occupancy.controller;

import no.avinor.gate_occupancy.dto.RestaurantDTO;
import no.avinor.gate_occupancy.service.RestaurantServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/nearby")
    public List<RestaurantDTO> getNearby(@RequestParam int gateId) {
        return restaurantService.getNearbyRestaurants(gateId);
    }
}
