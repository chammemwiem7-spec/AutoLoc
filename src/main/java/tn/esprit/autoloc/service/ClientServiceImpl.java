package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("L'identifiant doit être null pour la création");
        }
        if (client.getNom() == null || client.getNom().trim().isEmpty() ||
                client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom et l'email du client sont obligatoires");
        }
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client existant = getClientById(id);
        if (client.getNom() == null || client.getNom().trim().isEmpty() ||
                client.getEmail() == null || client.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom et l'email du client sont obligatoires");
        }
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepository.save(existant);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }
}