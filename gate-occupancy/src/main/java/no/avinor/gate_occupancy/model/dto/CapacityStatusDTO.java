package no.avinor.gate_occupancy.model.dto;

import no.avinor.gate_occupancy.model.entities.Crowdiness;

import java.time.LocalDateTime;

/**
 * DTO representing a single measurement or snapshot of crowdiness in an area.
 * Typically used if you want to send historical or time-based data.
 */
public class CapacityStatusDTO {

    private Crowdiness crowdiness;         // Status value, "LOW", "MODERATE", "HIGH"
    private LocalDateTime timestamp;   // When the measurement was taken //greit å ha om vi evt ikke bruker sanntid?

    public CapacityStatusDTO() {}

    public CapacityStatusDTO(Crowdiness crowdiness, LocalDateTime timestamp) {
        this.crowdiness = crowdiness;
        this.timestamp = timestamp;
    }

    public Crowdiness getCrowdiness() {
        return crowdiness;
    }

    public void setCrowdiness(Crowdiness crowdiness) {
        this.crowdiness = crowdiness;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
