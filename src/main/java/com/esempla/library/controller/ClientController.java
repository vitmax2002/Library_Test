package com.esempla.library.controller;

import com.esempla.library.service.ClientService;
import com.esempla.library.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

   private final ClientService clientService;

   public ClientController(ClientService clientService)
   {
       this.clientService=clientService;
   }

    @GetMapping("/show")
    public ResponseEntity<List<Client>> showClients()
   {
       return new ResponseEntity<>(clientService.showClients(), HttpStatus.FOUND);
   }


   @PostMapping("/save")
    public ResponseEntity<Client> createClient(@RequestBody Client client)
   {
       return new ResponseEntity<>(clientService.createClient(client),HttpStatus.CREATED);
   }
}
