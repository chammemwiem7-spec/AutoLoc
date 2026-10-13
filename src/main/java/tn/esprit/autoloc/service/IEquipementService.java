package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Equipement;

public interface IEquipementService {
    Equipement saveEquipement(Equipement equipement);
    Equipement getEquipementById(Long id);
    List<Equipement> getAllEquipements();
    Equipement updateEquipement(Long id, Equipement equipement);
    void deleteEquipement(Long id);
}