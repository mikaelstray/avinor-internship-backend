package no.avinor.gate_occupancy.model;

import java.time.LocalDateTime;

/**
 * Represents the crowd level or availability in a specific area at a given point in time.
 * Used to evaluate real-time or recent usage data.
 */
public class CapacityStatus {
    private Long id;                  // Unique identifier for this status record
    private String crowdiness;        // Current status (e.g., "LOW", "MODERATE", "HIGH")
    private LocalDateTime timestamp;  // Time when this status was recorded

    private Long areaId;              // ID of the area this status applies to

    public CapacityStatus() {}

    public CapacityStatus(Long id, String crowdiness, LocalDateTime timestamp, Long areaId) {
        this.id = id;
        this.crowdiness = crowdiness;
        this.timestamp = timestamp;
        this.areaId = areaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
