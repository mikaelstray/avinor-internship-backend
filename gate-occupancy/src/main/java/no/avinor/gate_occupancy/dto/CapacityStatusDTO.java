package no.avinor.gate_occupancy.dto;

import java.time.LocalDateTime;

/**
 * DTO representing a single measurement or snapshot of crowdiness in an area.
 * Typically used if you want to send historical or time-based data.
 */
public class CapacityStatusDTO {

    private String crowdiness;         // Status value, "LOW", "MODERATE", "HIGH"
    private LocalDateTime timestamp;   // When the measurement was taken //greit å ha om vi evt ikke bruker sanntid?

    public CapacityStatusDTO() {}

    public CapacityStatusDTO(String crowdiness, LocalDateTime timestamp) {
        this.crowdiness = crowdiness;
        this.timestamp = timestamp;
    }

    public String getCrowdiness() {
        return crowdiness;
    }

    public void setCrowdiness(String crowdiness) {
        this.crowdiness = crowdiness;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
