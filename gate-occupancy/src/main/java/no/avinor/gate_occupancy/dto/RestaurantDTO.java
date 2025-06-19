package no.avinor.gate_occupancy.dto;

public class RestaurantDTO {
    private String name;
    private int distance;
    private boolean hasSeating;

    public RestaurantDTO(String name, int distance, boolean hasSeating) {
        this.name = name;
        this.distance = distance;
        this.hasSeating = hasSeating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isHasSeating() {
        return hasSeating;
    }

    public void setHasSeating(boolean hasSeating) {
        this.hasSeating = hasSeating;
    }
}
