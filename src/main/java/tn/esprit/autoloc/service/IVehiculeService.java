package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import java.util.List;

public interface IVehiculeService {
    List<Vehicule> getAllVehicules();
    Vehicule getVehiculeById(Long id);
    Vehicule saveVehicule(Vehicule vehicule);
    void deleteVehicule(Long id);
}