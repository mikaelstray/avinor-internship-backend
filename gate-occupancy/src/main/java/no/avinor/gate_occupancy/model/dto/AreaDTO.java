package no.avinor.gate_occupancy.model.dto;

import no.avinor.gate_occupancy.model.entities.Crowdiness;

import java.util.List;

/**
 * DTO that encapsulates all information needed
 * by the frontend to display an area and its current crowdiness level.
 */
public class AreaDTO {

    private String area;               // Name of the area, e.g. "Departure Area A"
    private String airport;           // Name of the airport, e.g. "Oslo Lufthavn"
    private String terminal;          // Which terminal this area is in, e.g. "Terminal 1"
    private List<String> areaTypes;   // Types inside this area, e.g. ["Gate A5", "Burger King"]
    private Crowdiness latestCrowdiness;  // Current crowdiness status

    public AreaDTO() {}

    public AreaDTO(String area, String airport, String terminal,
                   List<String> areaTypes, Crowdiness latestCrowdiness) {
        this.area = area;
        this.airport = airport;
        this.terminal = terminal;
        this.areaTypes = areaTypes;
        this.latestCrowdiness = latestCrowdiness;
    }

    public String getAreaName() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminalName(String terminal) {
        this.terminal = terminal;
    }

    public List<String> getAreaTypes() {
        return areaTypes;
    }

    public void setAreaTypes(List<String> areaTypes) {
        this.areaTypes = areaTypes;
    }

    public Crowdiness getLatestCrowdiness() {
        return latestCrowdiness;
    }

    public void setLatestCrowdiness(Crowdiness latestCrowdiness) {
        this.latestCrowdiness = latestCrowdiness;
    }
}
