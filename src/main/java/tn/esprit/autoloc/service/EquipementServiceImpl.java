package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.EquipementRepository;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    public Equipement saveEquipement(Equipement equipement) {
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null");
        }
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement getEquipementById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement updateEquipement(Long id, Equipement equipement) {
        Equipement existant = getEquipementById(id);
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    public void deleteEquipement(Long id) {
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        equipementRepository.deleteById(id);
    }
}