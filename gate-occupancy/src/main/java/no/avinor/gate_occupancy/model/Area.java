package no.avinor.gate_occupancy.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Core domain object representing a physical area at the airport,
 * such as a gate, seating area, or restaurant.
 */
public class Area {
    private Long id;
    private String name;           // Display name, område med flere gates restauranter feks
    private Terminal terminal;      //Terminal this area is located in
    private Set<Place> places; // Logical type of area ("Gate", "Restaurant")
    private int capacity;
    private double size;

    public Area() {}

    public Area(Long id, String name, Terminal terminal, Set<Place> places) {
        this.id = id;
        this.name = name;
        this.terminal = terminal;
        this.places = places;
    }

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

    public Airport getAirport() {
        return terminal != null ? terminal.getAirport() : null;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    public Terminal getTerminal(){
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void addPlace(Place place) {
        if (this.places == null) {
            this.places = new HashSet<>();
        }
        this.places.add(place);
    }

    public int getCapacity() {
        return capacity; }

    public void setCapacity(int capacity) {
        this.capacity = capacity; }

    public double getSize() {
        return size; }

    public void setSize(double size) {
        this.size = size; }


    public Set<Place> getPlaces() {
        if (places == null) {
            return new HashSet<>();
        }
        return places;
    }




}
