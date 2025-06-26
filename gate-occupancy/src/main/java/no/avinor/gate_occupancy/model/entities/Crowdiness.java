package no.avinor.gate_occupancy.model.entities;

public enum Crowdiness {
    LOW("Low crowdiness"),
    MODERATE("Moderate crowdiness"),
    HIGH("High crowdiness"),
    UNKNOWN ("UNKNOWN");

    private final String description;

    Crowdiness(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
