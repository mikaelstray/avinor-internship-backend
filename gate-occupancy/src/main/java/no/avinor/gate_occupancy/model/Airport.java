package no.avinor.gate_occupancy.model;


import java.util.List;

/**
 * Simple model representing an airport.
 * Used to associate areas (like gates or lounges) with their physical airport location.
 */
public class Airport {
    private String id;        // Unique identifier for the airport
    private String name;
    private List<Terminal> terminals;

    public Airport() {}

    public Airport(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

