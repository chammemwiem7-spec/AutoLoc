package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import java.util.List;

public interface IAgenceService {
    List<Agence> getAllAgences();
    Agence getAgenceById(Long id);
    Agence saveAgence(Agence agence);
    void deleteAgence(Long id);
}