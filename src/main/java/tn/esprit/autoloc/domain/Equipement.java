package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "equipement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEquipement;

    @Column(nullable = false, unique = true, length = 50)
    String libelle;

    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    List<Vehicule> vehicules = new ArrayList<>();
}