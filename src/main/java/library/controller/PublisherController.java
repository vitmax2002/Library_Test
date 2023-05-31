package library.controller;

import library.model.Publisher;
import library.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Publisher>> getAll()
    {
        return new ResponseEntity<>(publisherService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Publisher> getById(@PathVariable("id") int id)
    {
        return new ResponseEntity<>(publisherService.getById(id),HttpStatus.FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher)
    {
        return new ResponseEntity<>(publisherService.createPublisher(publisher),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id,@RequestParam String name)
    {
        return new ResponseEntity<>(publisherService.updatePublisher(id,name),HttpStatus.ACCEPTED);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable("id") int id)
    {
         return new ResponseEntity<>(publisherService.delete(id),HttpStatus.OK);
    }


}
