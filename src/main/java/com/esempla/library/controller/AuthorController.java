package com.esempla.library.controller;

import com.esempla.library.model.Author;
import com.esempla.library.service.AuthorService;
import com.esempla.library.service.dto.AuthorDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private Logger log= LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<Author>> getAll() {
        log.debug("Rest request to view all authors");
        List<Author> author=authorService.getAll();
        return ResponseEntity.ok().body(author);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") int id) {
        log.debug("Searching author by id {}",id);
        Author author=authorService.getById(id);
        return ResponseEntity.ok().body(author);
    }
    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto author) {
        Author authorAdd=authorService.createAuthor(author);
        log.debug("Author {} was added",author);
        return ResponseEntity.ok().body(authorAdd);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateBook(@PathVariable int id, @RequestBody Author author) {
        Author authorUpdate=authorService.updateAuthor(id, author);
        log.debug("Author with id {} was updated",id);
        return ResponseEntity.ok().body(authorUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        authorService.delete(id);
        log.debug("Author with id {} was deleted",id);
        return ResponseEntity.noContent().build();
    }
}