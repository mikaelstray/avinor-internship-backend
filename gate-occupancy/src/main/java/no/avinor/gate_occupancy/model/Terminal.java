package no.avinor.gate_occupancy.model;

import javax.swing.undo.CannotRedoException;
import java.util.List;
import java.util.Set;

public class Terminal {
    private Long id;
    private String name;
    private Airport airport;
    private Set<Area> areas;

    public Terminal() {
    }

    public Terminal(Long id, String name, Airport airport) {
        this.id = id;
        this.name = name;
        this.airport = airport;
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

    public Set<Area> getAreas(){
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
}

