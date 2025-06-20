package no.avinor.gate_occupancy.model;


/**
 * Simple model representing an airport.
 * Used to associate areas (like gates or lounges) with their physical airport location.
 */
public class Airport {
    private Long id;        // Unique identifier for the airport
    private String name;    // Airport name ("OSL")

    public Airport() {}

    public Airport(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
