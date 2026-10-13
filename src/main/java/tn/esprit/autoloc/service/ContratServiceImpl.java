package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ContratRepository; // Aw IContratRepository selon esm el repository mte3ek

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;

    @Override
    public Contrat saveContrat(Contrat contrat) {
        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat getContratById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Long id, Contrat contrat) {
        Contrat existant = getContratById(id);
        if (contrat.getMontantTotal() != null && contrat.getMontantTotal().signum() < 0) {
            throw new IllegalArgumentException("Le montant total ne peut pas être négatif");
        }
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setDateSignature(contrat.getDateSignature());
        existant.setValide(contrat.isValide());
        return contratRepository.save(existant);
    }

    @Override
    public void deleteContrat(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }
}