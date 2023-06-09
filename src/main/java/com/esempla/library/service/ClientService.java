package com.esempla.library.service;

import com.esempla.library.model.Client;
import com.esempla.library.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private Logger log= LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository)
    {
        this.clientRepository=clientRepository;
    }

    public Client createClient(Client client)
    {
        log.debug("Adaugarea clientului in baza de date");
        return clientRepository.save(client);
    }
    public List<Client> showClients()
    {
        log.debug("Toti clientii");
        return clientRepository.findAll();
    }
}
