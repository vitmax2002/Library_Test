package com.esempla.library.controller;

import com.esempla.library.service.ClientService;
import com.esempla.library.model.Client;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/client")
public class ClientController {

    private Logger log= LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

   public ClientController(ClientService clientService)
   {
       this.clientService=clientService;
   }


    @GetMapping("/show")
    public ResponseEntity<List<Client>> showClients()
   {
       log.debug("Rest request to view all clients");
       List<Client> clients=clientService.showClients();
       return ResponseEntity.ok().body(clients);
   }

   @PostMapping("/save")
    public ResponseEntity<Client> createClient(@RequestBody Client client)
   {
       Client clientCreate=clientService.createClient(client);
       log.debug("Client {} was created",client);
       return ResponseEntity.ok().body(clientCreate);
   }
}
