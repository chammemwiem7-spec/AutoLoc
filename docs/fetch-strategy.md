# Note technique : Stratégies de Fetch et Cascade (AutoLoc)

Ce document résume les choix de conception concernant les associations JPA, les stratégies de chargement (`FetchType`) et la propagation du cycle de vie (`CascadeType` et `orphanRemoval`) pour le projet AutoLoc.

| Association | Fetch | Cascade / OrphanRemoval | Justification                                                                                        |
| :--- | :--- | :--- |:-----------------------------------------------------------------------------------------------------|
| **Contrat → Paiement** | LAZY | ALL + orphanRemoval | Un paiement n'existe que rattaché à son contrat ; supprimer le contrat supprime ses paiements.       |
| **Agence → Vehicule** | LAZY | Aucune | Un véhicule survit à la suppression de son agence.                                                   |
| **Agence → Employe** | LAZY | Aucune | Un employé peut être géré indépendamment ou réaffecté au sein de l'agence.                           |
| **Vehicule → Equipement** | LAZY | Aucune | Les équipements sont partagés entre plusieurs véhicules (relation ManyToMany via table de jointure). |
| **Client → Reservation** | LAZY | PERSIST | Permet d'enregistrer les réservations en cascade lors de la création d'un client.                    |
| **Reservation → Vehicule** | LAZY | Aucune | Une réservation pointe vers un véhicule existant sans le modifier ni le supprimer.                   |
| **Reservation → Contrat** | LAZY | ALL + orphanRemoval | Un contrat est directement lié à une seule réservation (composition).                                |
| **Vehicule → Maintenance** | LAZY | PERSIST | Permet de persister les maintenances associées au véhicule en base.                                  |