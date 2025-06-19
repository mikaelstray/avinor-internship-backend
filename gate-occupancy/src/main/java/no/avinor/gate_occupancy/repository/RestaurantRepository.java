package no.avinor.gate_occupancy.repository;

import no.avinor.gate_occupancy.model.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantInfo, Long> {
    List<RestaurantInfo> findByGateId(int gateId);
}
