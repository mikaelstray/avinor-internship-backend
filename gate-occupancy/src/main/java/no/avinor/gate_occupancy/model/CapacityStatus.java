package no.avinor.gate_occupancy.model;

import no.avinor.gate_occupancy.model.Crowdiness;

import java.time.LocalDateTime;

public class CapacityStatus {
    private Long id;                  // Unik identifikator for denne statusen
    private Crowdiness crowdiness;    // Bruk enum i stedet for en streng
    private LocalDateTime timestamp;  // Tidspunkt for når denne statusen ble registrert
    private Long areaId;              // ID for området denne statusen gjelder for

    public CapacityStatus() {}

    public CapacityStatus(Long id, Crowdiness crowdiness, LocalDateTime timestamp, Long areaId) {
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
