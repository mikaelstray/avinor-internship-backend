package no.avinor.gate_occupancy.model;

/**
 * Core domain object representing a physical area at the airport,
 * such as a gate, seating area, or restaurant.
 */
public class Area {
    private Long id;               // Unique ID for the area
    private String name;           // Display name ("Gate A5")
    private String airportName;    // Airport this area belongs to// T
    private String areaTypeName;   // Logical type of area ("Gate", "Restaurant")

    public Area() {}

    public Area(Long id, String name, String airportName, String areaTypeName) {
        this.id = id;
        this.name = name;
        this.airportName = airportName;
        this.areaTypeName = areaTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
