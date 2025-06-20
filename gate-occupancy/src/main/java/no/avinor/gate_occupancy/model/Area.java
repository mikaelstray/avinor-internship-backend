package no.avinor.gate_occupancy.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Core domain object representing a physical area at the airport,
 * such as a gate, seating area, or restaurant.
 */
public class Area {
    private Long id;               // Unique ID for the area
    private String name;           // Display name, område med flere gates restauranter feks
    private Airport airport;        // Airport this area belongs to//
    private Terminal terminal;      //Terminal this area is located in
    private Set<AreaType> areaTypes;   // Logical type of area ("Gate", "Restaurant")

    public Area() {}

    public Area(Long id, String name, Airport airport, Terminal terminal, Set<AreaType> areaTypes) {
        this.id = id;
        this.name = name;
        this.airport = airport;
        this.terminal = terminal;
        this.areaTypes = areaTypes;
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public void setAreaTypes(Set<AreaType> areaTypes) {
        this.areaTypes = areaTypes;
    }

    public Terminal getTerminal(){
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void addAreaType(AreaType areaType) {
        if (this.areaTypes == null) {
            this.areaTypes = new HashSet<>();
        }
        this.areaTypes.add(areaType);
    }

    public Set<AreaType> getAreaTypes() {
        return areaTypes;
    }



}
