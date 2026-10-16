# Note technique : Couche Repository (AutoLoc)

## A. Choix des interfaces Repository
| Interface             | Étend | Justification |
|:----------------------| :--- | :--- |
| ContratRepository     | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| ClientRepository      | JpaRepository<Client, Long> | CRUD complet, gestion des clients et méthodes de persistance JPA. |
| AgenceRepository      | JpaRepository<Agence, Long> | CRUD complet, gestion des agences et méthodes de persistance JPA. |
| EmployeRepository     | JpaRepository<Employe, Long> | CRUD complet, gestion des employés et méthodes de persistance JPA. |
| VehiculeRepository    | JpaRepository<Vehicule, Long> | CRUD complet, gestion des véhicules et méthodes de persistance JPA. |
| EquipementRepository  | JpaRepository<Equipement, Long> | CRUD complet, gestion des équipements et méthodes de persistance JPA. |
| ReservationRepository | JpaRepository<Reservation, Long> | CRUD complet, gestion des réservations et méthodes de persistance JPA. |
| PaiementRepository    | JpaRepository<Paiement, Long> | CRUD complet ; les paiements sont principalement gérés par cascade via le Contrat[cite: 15]. |
| MaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet, gestion de la maintenance et méthodes de persistance JPA. |

## B. Anomalies SonarQube for IDE (SonarLint)
| Anomalie SonarQube for IDE | Règle / explication | Correction apportée |
| :--- | :--- | :--- |
| Imports inutilisés | Présence d'imports de bibliothèques non utilisés dans les interfaces repository. | Nettoyage et suppression des imports superflus pour respecter le Clean Code. |
| Respect de la convention de nommage | Les interfaces doivent obligatoirement commencer par le préfixe `I` dans la convention AutoLoc. | Vérification et validation que toutes les interfaces commencent par `I` (ex: `IVehiculeRepository`)[cite: 14, 15]. |
| Documentation / Commentaires | Absence de javadoc ou commentaires descriptifs sur certaines interfaces spécifiques. | Ajout de notes descriptives pour clarifier le rôle de chaque repository. |