package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;
import java.util.List;

public interface IEmployeService {
    List<Employe> getAllEmployes();
    Employe getEmployeById(Long id);
    Employe saveEmploye(Employe employe);
    void deleteEmploye(Long id);
}