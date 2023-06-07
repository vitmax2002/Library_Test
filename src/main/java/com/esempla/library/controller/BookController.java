package com.esempla.library.controller;

import com.esempla.library.service.BookService;
import com.esempla.library.model.Book;
import com.esempla.library.service.dto.BookSaveDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Api(protocols = "http",value = "Books management")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/show")
    @ApiOperation(value = "O descriere la endpoint")
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.getById(id),HttpStatus.FOUND);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookSaveDto dto) {
        return new ResponseEntity<>(bookService.createBook(dto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody BookSaveDto book) {
        return new ResponseEntity<>(bookService.updateBook(id, book),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.delete(id),HttpStatus.ACCEPTED);
    }


}