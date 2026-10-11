package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMaintenance;

    @Column(nullable = false)
    LocalDate dateDebut;

    @Column(nullable = false)
    LocalDate dateFin;

    String description;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule")
    Vehicule vehicule;
}