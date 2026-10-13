package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Contrat;

public interface IContratService {
    Contrat saveContrat(Contrat contrat);
    Contrat getContratById(Long id);
    List<Contrat> getAllContrats();
    Contrat updateContrat(Long id, Contrat contrat);
    void deleteContrat(Long id);
}