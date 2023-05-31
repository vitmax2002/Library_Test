package library.controller;

import library.model.Author;
import library.model.AuthorDto;
import library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Author>> getAll() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorService.getById(id),HttpStatus.FOUND);
    }
    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto author) {
        return new ResponseEntity<>(authorService.createAuthor(author),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateBook(@PathVariable int id, @RequestBody Author author) {
        return new ResponseEntity<>(authorService.updateAuthor(id, author),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorService.delete(id),HttpStatus.ACCEPTED);
    }
}