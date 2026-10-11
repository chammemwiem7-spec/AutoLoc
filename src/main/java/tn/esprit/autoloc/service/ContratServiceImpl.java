package tn.esprit.autoloc.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContratServiceImpl implements IContratService {

    ContratRepository contratRepository;

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Contrat saveContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    @Transactional
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }
}