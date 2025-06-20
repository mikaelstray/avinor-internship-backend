package no.avinor.gate_occupancy.dto;

/**
 * Data Transfer Object (DTO) that encapsulates all information needed
 * by the frontend to display an area and its current crowdiness level.
 */
public class AreaDTO {

    private String areaName;         // Display name for the area ("Gate A5")
    private String airportName;      // Name of the airport the area belongs to
    private String areaTypeName;     // Category/type of area ("Gate", "Lounge")
    private String latestCrowdiness; // Current status (LOW, MODERATE, HIGH, etc.)

    public AreaDTO() {}

    public AreaDTO(String areaName, String airportName, String areaTypeName, String latestCrowdiness) {
        this.areaName = areaName;
        this.airportName = airportName;
        this.areaTypeName = areaTypeName;
        this.latestCrowdiness = latestCrowdiness;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAreaTypeName() {
        return areaTypeName;
    }

    public void setAreaTypeName(String areaTypeName) {
        this.areaTypeName = areaTypeName;
    }

    public String getLatestCrowdiness() {
        return latestCrowdiness;
    }

    public void setLatestCrowdiness(String latestCrowdiness) {
        this.latestCrowdiness = latestCrowdiness;
    }
}
