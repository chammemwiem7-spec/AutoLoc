# Note technique : API REST et Documentation (AutoLoc)

## A. Endpoints de l'API
| Contrôleur | Verbe & Route | Service appelé | Code succès | Codes d'erreur |
| :--- | :--- | :--- | :--- | :--- |
| AgenceController | GET /api/agences | agenceService.getAllAgences() | 200 | - |
| AgenceController | GET /api/agences/{id} | agenceService.getAgenceById() | 200 | 404 |
| AgenceController | POST /api/agences | agenceService.saveAgence() | 201 | 400 |
| AgenceController | PUT /api/agences/{id} | agenceService.saveAgence() | 200 | 404, 400 |
| AgenceController | DELETE /api/agences/{id} | agenceService.deleteAgence() | 204 | 404 |
| ContratController | GET /api/contrats | contratService.getAllContrats() | 200 | - |
| ContratController | GET /api/contrats/{id} | contratService.getContratById() | 200 | 404 |
| ContratController | POST /api/contrats | contratService.saveContrat() | 201 | 400 |
| ContratController | PUT /api/contrats/{id} | contratService.saveContrat() | 200 | 404, 400 |
| ContratController | DELETE /api/contrats/{id} | contratService.deleteContrat() | 204 | 404 |
| PaiementController | GET /api/paiements | paiementService.getAllPaiements() | 200 | - |
| PaiementController | GET /api/paiements/{id} | paiementService.getPaiementById() | 200 | 404 |

## B. Annotations Jackson
- **Contrat / Paiement** : Utilisation de `@JsonManagedReference` (côté parent `Contrat`) et `@JsonBackReference` (côté enfant `Paiement`) pour gérer la composition et éviter les boucles infinies `StackOverflowError`[cite: 15].
- **Différence `@JsonBackReference` / `@JsonIgnore`** : `@JsonBackReference` gère la sérialisation bidirectionnelle en ignorant la partie inverse lors de l'affichage du parent mais en permettant de remonter au parent, tandis que `@JsonIgnore` masque totalement le champ dans le JSON.

## C. Postman et Swagger
- **Postman** : Utilisé pour tester dynamiquement les requêtes HTTP (nominaux, cas 404, cas 400, scénario de cascade `Contrat/Paiement`)[cite: 15].
- **Swagger UI** : Interface web interactive générée automatiquement par SpringDoc permettant d'explorer et de tester visuellement les endpoints connectés au code source[cite: 15].

## D. Anomalies SonarQube for IDE
| Anomalie | Règle / Explication | Correction apportée |
| :--- | :--- | :--- |
| Imports inutilisés | Présence d'imports de bibliothèques non utilisés dans les contrôleurs. | Nettoyage des imports superflus. |
| Injection par attribut | Utilisation de `@Autowired` sur les champs au lieu de l'injection par constructeur. | Utilisation de champs `final` avec `@RequiredArgsConstructor`. |

## E. Questions de compréhension
1. **Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?**
    - Le `DispatcherServlet` est le point d'entrée unique[cite: 14]. Il s'appuie sur le `HandlerMapping` pour identifier la méthode et le contrôleur cibles en fonction du verbe HTTP et de l'URL[cite: 14].
2. **Pourquoi ContratController dépend-il de IContratService et non de ContratRepository ni de ContratServiceImpl ?**
    - Pour respecter le principe d'architecture en couches (separation of concerns), l'injection par interface (faible couplage) et garantir que toute la logique métier et les règles de gestion du service sont bien appliquées.
3. **Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?**
    - `201 Created` indique qu'une ressource a été créée avec succès. `204 No Content` indique une suppression réussie sans corps de réponse. Le `404 Not Found` est levé proprement (via le `GlobalExceptionHandler` pour une `ResourceNotFoundException`), contrairement à une exception non gérée qui renverrait un `500 Server Error`[cite: 14, 15].
4. **Pourquoi PaiementController n'expose-t-il que des GET ? Quelle réponse reçoit un POST /api/paiements ?**
    - Les paiements sont gérés par cascade via le `Contrat` (le service de paiement est en lecture seule). Un `POST /api/paiements` direct retourne un code `405 Method Not Allowed`.
5. **Quels risques présente l'exposition directe des entités JPA ? Pourquoi Contrat.paiements utilise-t-il @JsonManagedReference alors que Agence.vehicules utilise @JsonIgnore ?**
    - L'exposition directe couple l'API à la base de données et expose les structures internes. `@JsonManagedReference` gère la sérialisation en cascade de la composition parent/enfant (`Contrat`/`Paiement`), tandis que `@JsonIgnore` rompt la liaison pour éviter les boucles de sérialisation sur des relations complexes ou optionnelles[cite: 15].