package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Paiement;

public interface IPaiementService {
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
}