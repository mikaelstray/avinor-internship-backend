package no.avinor.gate_occupancy.model;

/**
 * Represents a specific type or unit within an area.
 * Example: Gate A5, Restaurant X, Lounge Y
 */
public class AreaType {
    private Long id;
    private String name; // e.g., "Gate A5", "Burger King", "Lounge Gold"

    public AreaType() {}

    public AreaType(Long id, String name) {
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
