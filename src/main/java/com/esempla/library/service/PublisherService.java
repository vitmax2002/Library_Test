package com.esempla.library.service;

import com.esempla.library.model.Publisher;
import com.esempla.library.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PublisherService {
    private Logger log= LoggerFactory.getLogger(PublisherService.class);
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher createPublisher(Publisher publisher)
    {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher getById(int id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitatea nu a fost găsită."));
    }

    public Publisher updatePublisher(int id, String nume) {
      Publisher existingPublisher = publisherRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        log.debug("Setting new name for publisher");
        existingPublisher.setName(nume);
        return publisherRepository.save(existingPublisher);
    }

    public void delete(int id) {
        publisherRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        log.debug("Removing publisher");
        publisherRepository.deleteById(id);
    }




}
