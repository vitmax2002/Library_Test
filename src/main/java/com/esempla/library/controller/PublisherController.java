package com.esempla.library.controller;


import com.esempla.library.service.PublisherService;
import com.esempla.library.model.Publisher;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/publisher")
public class PublisherController {
    private Logger log= LoggerFactory.getLogger(PublisherController.class);

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @GetMapping("/show")
    public ResponseEntity<List<Publisher>> getAll()
    {
        log.debug("Rest request to view all publishers");
        List<Publisher> publishers=publisherService.getAll();
        return ResponseEntity.ok().body(publishers);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Publisher> getById(@PathVariable("id") int id)
    {
        Publisher publisher=publisherService.getById(id);
        log.debug("Publisher with id {} is {}",id,publisher);
        return ResponseEntity.ok().body(publisher);
    }


    @PostMapping("/add")
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher)
    {
        Publisher publisherAdd=publisherService.createPublisher(publisher);
        log.debug("Publisher {} was added",publisher);
        return ResponseEntity.ok().body(publisherAdd);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id,@RequestParam String name)
    {
        Publisher publisher=publisherService.updatePublisher(id,name);
        log.debug("Publisher with id {} was updated",id);
        return ResponseEntity.ok().body(publisher);
    }


    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable("id") int id)
    {
        publisherService.delete(id);
        log.debug("Publisher with id {} was deleted",id);
         return ResponseEntity.noContent().build();
    }


}
