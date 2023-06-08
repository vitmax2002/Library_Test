package com.esempla.library.controller;

import com.esempla.library.service.BookService;
import com.esempla.library.model.Book;
import com.esempla.library.service.dto.BookSaveDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private Logger log= LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/show")
    public ResponseEntity<List<Book>> getAll() {
        log.debug("Rest request to view all books");
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") String id) {
        Book book=bookService.getById(id);
        log.debug("Book with id {} is {}",id,book);
        return ResponseEntity.ok().body(book);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookSaveDto dto) {
        Book book=bookService.createBook(dto);
        return ResponseEntity.ok().body(book);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody BookSaveDto book) {
        Book bookUpdate=bookService.updateBook(id, book);
        return ResponseEntity.ok().body(bookUpdate);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") String id) {
        bookService.delete(id);
        log.debug("book with id {} was deleted",id);
        return ResponseEntity.noContent().build();
    }


}