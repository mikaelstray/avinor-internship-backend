package no.avinor.gate_occupancy.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)

@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer capacity;

    @Column
    private LocationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

}


