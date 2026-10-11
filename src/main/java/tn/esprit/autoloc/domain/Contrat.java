package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "contrat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idContrat;

    @Column(nullable = false)
    LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal montantTotal;

    boolean valide;

    @OneToOne
    @JoinColumn(name = "id_reservation")
    Reservation reservation;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Paiement> paiements = new ArrayList<>();
}