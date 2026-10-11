package tn.esprit.autoloc.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AgenceServiceImpl implements IAgenceService {

    AgenceRepository agenceRepository;

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence getAgenceById(Long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Agence saveAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    @Transactional
    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }
}