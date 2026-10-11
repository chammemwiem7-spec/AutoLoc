package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "client")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idClient;

    @Column(nullable = false, length = 50)
    String nom;

    @Column(nullable = false, length = 50)
    String prenom;

    @Column(nullable = false, unique = true)
    String email;

    String telephone;

    @Column(nullable = false, unique = true)
    String numPermis;

    LocalDate dateInscription;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Reservation> reservations = new ArrayList<>();
}