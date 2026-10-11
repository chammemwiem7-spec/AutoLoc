package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "agence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAgence;

    String nom;
    String ville;
    String adresse;
    String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    List<Vehicule> vehicules = new ArrayList<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    List<Employe> employes = new ArrayList<>();
}