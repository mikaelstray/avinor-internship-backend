package no.avinor.gate_occupancy.model;

import jakarta.persistence.*;
import no.avinor.gate_occupancy.model.enums.CapacityStatus;

@Entity
public class GateOccupancy {

    @Id
    private int gateId;
    private int totalSeats;
    private int freeSeats;

    @Enumerated(EnumType.STRING)
    private CapacityStatus status;

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public CapacityStatus getStatus() {
        return status;
    }

    public void setStatus(CapacityStatus status) {
        this.status = status;
    }
}
