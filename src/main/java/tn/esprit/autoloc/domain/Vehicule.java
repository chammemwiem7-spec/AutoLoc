package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "vehicule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idVehicule;

    String immatriculation;
    String marque;
    String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    StatutVehicule statut;

    // Association Agence -> Vehicule (Côté propriétaire)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agence")
    Agence agence;

    // Association Vehicule -> Equipement (ManyToMany propriétaire avec table de jointure)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "id_vehicule"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement")
    )
    List<Equipement> equipements = new ArrayList<>();

    // Association Vehicule -> Maintenance (Côté inverse)
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Maintenance> maintenances = new ArrayList<>();
}