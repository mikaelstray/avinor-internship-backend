package no.avinor.gate_occupancy.model;

/**
 * Represents a specific type or unit within an area.
 * Example: Gate A5, Restaurant X, Lounge Y
 */
public class Place {
    private Long id;
    private String name;
    private int capacity;

    private CapacityStatus capacityStatus;

    public Place() {}

    public Place(Long id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacityStatus(CapacityStatus capacityStatus){
        this.capacityStatus = capacityStatus;
    }

    public CapacityStatus getCapacityStatus() {
        return capacityStatus;
    }
}
