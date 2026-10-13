# Note technique : Couche Service, IoC et Injection de Dépendances (AutoLoc)

## A. Modes d'injection comparés
| Critère | Injection par constructeur (Retenu) | Injection par attribut (@Autowired) |
| :--- | :--- | :--- |
| **Champ final** | Oui (sécurité et immutabilité) | Non |
| **Dépendance visible** | Oui (dans la signature) | Cachée |
| **Utilisable hors conteneur** | Oui (`new Service(repo)`) | Non (retourne null) |
| **SonarQube / Clean Code** | Conforme aux bonnes pratiques | Signalé comme anomalie |

## B. Récapitulatif des services
| Service | Dépendances injectées | Mode | Justification |
| :--- | :--- | :--- | :--- |
| ContratServiceImpl | ContratRepository | Constructeur | Logique métier des contrats |
| AgenceServiceImpl | AgenceRepository | Constructeur | Logique métier des agences |
| ClientServiceImpl | ClientRepository | Constructeur | Logique métier des clients |
| EmployeServiceImpl | EmployeRepository | Constructeur | Logique métier des employés |
| VehiculeServiceImpl | VehiculeRepository | Constructeur | Logique métier des véhicules |
| EquipementServiceImpl | EquipementRepository | Constructeur | Logique métier des équipements |
| ReservationServiceImpl | ReservationRepository | Constructeur | Logique métier des réservations |
| PaiementServiceImpl | PaiementRepository | Constructeur | Lecture seule (géré par Contrat) |
| MaintenanceServiceImpl | MaintenanceRepository | Constructeur | Logique métier des maintenances |

## C. Messages d'erreur du conteneur
1. **NoSuchBeanDefinitionException** : Annotation `@Service` oubliée ou classe en dehors du package scanné `tn.esprit.autoloc`.
2. **NoUniqueBeanDefinitionException** : Plusieurs beans candidats trouvés. Correction : utiliser `@Primary` ou `@Qualifier`.
3. **BeanCurrentlyInCreationException (Dépendance circulaire)** : Deux services s'injectent mutuellement. Correction : réarchitecturer pour extraire la logique commune.

## D. Anomalies SonarQube for IDE
1. **S6813** : Utilisation de l'injection par attribut au lieu du constructeur. Corrigée par l'utilisation de `@RequiredArgsConstructor` et de champs `final`.
2. **S112** : Utilisation d'une exception générique (`RuntimeException`) corrigée par l'utilisation de `ResourceNotFoundException`.
3. **Import inutile** : Nettoyage des imports superflus dans les classes de service.

## E. Questions de compréhension
1. **Où est le new de ContratServiceImpl et qui l'exécute ?** Il est exécuté dynamiquement par le conteneur Spring IoC lors du démarrage de l'application via le scan des composants.
2. **Pourquoi un contrôleur dépendra-t-il de IContratService et non de ContratServiceImpl ?** Pour respecter le principe du couplage faible et de l'abstraction.
3. **Pourquoi un service singleton doit-il rester sans état ?** Pour éviter les conflits et bugs de concurrence (race conditions) entre les différents threads utilisateurs.
4. **Pourquoi update charge-t-il l'entité existante avant d'appeler save ?** Pour préserver l'intégrité des associations et éviter d'écraser des collections (comme les paiements) par des valeurs nulles.
5. **Pourquoi IPaiementService n'expose ni création, ni modification, ni suppression ?** Parce que le paiement est géré en cascade (composition) directement depuis le contrat.