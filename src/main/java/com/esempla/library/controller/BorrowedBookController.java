package com.esempla.library.controller;

import com.esempla.library.service.BorrowedBookService;
import com.esempla.library.model.BorrowedBook;
import com.esempla.library.service.dto.BorrowedBookDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/borrow")
public class BorrowedBookController {
    private Logger log= LoggerFactory.getLogger(BorrowedBookController.class);
    private final BorrowedBookService borrowedBookService;

    public BorrowedBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestBody BorrowedBookDto bookDto)
    {
        BorrowedBook book=borrowedBookService.createBook(bookDto);
        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/show")
    public ResponseEntity<List<BorrowedBook>> showBorrowers()
    {
        log.debug("Rest request to view all borrowed books");
        List<BorrowedBook> books=borrowedBookService.getAll();
        return ResponseEntity.ok().body(books);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable int id)
    {
        borrowedBookService.delete(id);
        log.debug("Borrowed book with id {} was returned",id);
        return ResponseEntity.noContent().build();
    }
}
