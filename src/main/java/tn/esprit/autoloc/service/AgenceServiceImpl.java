package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.AgenceRepository;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public Agence saveAgence(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null pour la création");
        }
        if (agence.getNom() == null || agence.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
        return agenceRepository.save(agence);
    }

    @Override
    public Agence getAgenceById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence updateAgence(Long id, Agence agence) {
        Agence existant = getAgenceById(id);
        if (agence.getNom() == null || agence.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
        existant.setNom(agence.getNom());
        existant.setVille(agence.getVille());
        existant.setAdresse(agence.getAdresse());
        existant.setTelephone(agence.getTelephone());
        return agenceRepository.save(existant);
    }

    @Override
    public void deleteAgence(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepository.deleteById(id);
    }
}