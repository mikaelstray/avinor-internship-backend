package no.avinor.gate_occupancy.model;

public class Terminal {
    private Long id;
    private String name;
    private Airport airport; // mange terminaler per flyplass

    public Terminal() {}

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
}
