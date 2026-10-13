package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Client;

public interface IClientService {
    Client saveClient(Client client);
    Client getClientById(Long id);
    List<Client> getAllClients();
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}