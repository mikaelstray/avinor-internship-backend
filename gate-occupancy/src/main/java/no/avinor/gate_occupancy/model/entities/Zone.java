package no.avinor.gate_occupancy.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)

@Table(name = "zone", schema = "INTERNS_SCHEMA")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private Set<Location> locations;

    @Column
    private Integer capacity;

}
