package no.avinor.gate_occupancy.dto;

import no.avinor.gate_occupancy.model.enums.CapacityStatus;

public class GateOccupancyDTO {
    private int gateId;
    private String gateName;
    private int freeSeats;
    private CapacityStatus status;

    public GateOccupancyDTO(int gateId, String gateName, int freeSeats, CapacityStatus status) {
        this.gateId = gateId;
        this.gateName = gateName;
        this.freeSeats = freeSeats;
        this.status = status;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
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
