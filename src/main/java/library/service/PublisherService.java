package library.service;

import library.model.Publisher;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import library.repository.*;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PublisherService {
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
        existingPublisher.setName(nume);
        return publisherRepository.save(existingPublisher);
    }

    public String delete(int id) {
        Publisher existingPublisher = publisherRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        String s=existingPublisher.getName();
        publisherRepository.deleteById(id);
        return "Elementul cu numele "+ s+ " a fost sters cu succes";
    }




}
