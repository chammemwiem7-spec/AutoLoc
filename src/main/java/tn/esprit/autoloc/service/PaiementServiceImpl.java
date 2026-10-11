package tn.esprit.autoloc.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaiementServiceImpl implements IPaiementService {

    PaiementRepository paiementRepository;

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}