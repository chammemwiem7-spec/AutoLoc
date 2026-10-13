package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Agence;

public interface IAgenceService {
    Agence saveAgence(Agence agence);
    Agence getAgenceById(Long id);
    List<Agence> getAllAgences();
    Agence updateAgence(Long id, Agence agence);
    void deleteAgence(Long id);
}