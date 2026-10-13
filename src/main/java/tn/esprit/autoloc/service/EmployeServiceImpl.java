package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EmployeRepository;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    @Override
    public Employe saveEmploye(Employe employe) {
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null");
        }
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        return employeRepository.save(employe);
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe updateEmploye(Long id, Employe employe) {
        Employe existant = getEmployeById(id);
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        existant.setAgence(employe.getAgence());
        return employeRepository.save(existant);
    }

    @Override
    public void deleteEmploye(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }
}