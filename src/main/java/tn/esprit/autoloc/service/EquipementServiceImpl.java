package tn.esprit.autoloc.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EquipementServiceImpl implements IEquipementService {

    EquipementRepository equipementRepository;

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement getEquipementById(Long id) {
        return equipementRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    @Transactional
    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}