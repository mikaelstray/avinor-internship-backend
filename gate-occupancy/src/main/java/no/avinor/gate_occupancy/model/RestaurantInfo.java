package no.avinor.gate_occupancy.model;

import jakarta.persistence.*;

@Entity
public class RestaurantInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int distanceMeters;
    private int gateId;
    private boolean hasSeating; //evt denne istedenfor totalSeating og freeSeats siden vi ikke får restaurantene med
    private int totalSeats;
    private int freeSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public boolean isHasSeating() {
        return hasSeating;
    }

    public void setHasSeating(boolean hasSeating) {
        this.hasSeating = hasSeating;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }
}
