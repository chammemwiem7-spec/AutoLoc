package tn.esprit.autoloc.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeServiceImpl implements IEmployeService {

    EmployeRepository employeRepository;

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}