package no.avinor.gate_occupancy.model.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)

@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean schengen;

    @OneToMany(mappedBy = "airport",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Terminal> terminals = new ArrayList<>();
}

