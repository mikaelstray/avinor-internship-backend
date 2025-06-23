package no.avinor.gate_occupancy.model;

import java.time.LocalDateTime;

public class CapacityStatus {
    private Long id;                  // Unik identifikator for denne statusen
    private Crowdiness crowdiness;
    private LocalDateTime timestamp;  // Tidspunkt for når denne statusen ble registrert
    private Area area;

    public CapacityStatus() {}

    public CapacityStatus(Long id, Crowdiness crowdiness, LocalDateTime timestamp, Area area) {
        this.id = id;
        this.crowdiness = crowdiness;
        this.timestamp = timestamp;
        this.area = area;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
