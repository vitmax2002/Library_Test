package library.service;

import library.model.Client;
import library.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository)
    {
        this.clientRepository=clientRepository;
    }

    public Client createClient(Client client)
    {
        return clientRepository.save(client);
    }
    public List<Client> showClients()
    {
        return clientRepository.findAll();
    }

    public Client createClients(Client client)
    {
        client.getBorrowedBooks();
       return clientRepository.save(client);
    }
}
