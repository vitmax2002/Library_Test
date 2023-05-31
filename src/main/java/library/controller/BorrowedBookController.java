package library.controller;

import library.model.BorrowedBook;
import library.model.BorrowedBookDto;
import library.service.BorrowedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowedBookController {

   private final BorrowedBookService borrowedBookService;

    public BorrowedBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }


    @PostMapping("/add")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestBody BorrowedBookDto bookDto)
    {
        return new ResponseEntity<>(borrowedBookService.createBook(bookDto),HttpStatus.CREATED);
    }

    @GetMapping("/show")
    public ResponseEntity<List<BorrowedBook>> showBorrowers()
    {
        return new ResponseEntity<>(borrowedBookService.getAll(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> returnBook(@PathVariable int id)
    {
        return new ResponseEntity<>(borrowedBookService.delete(id),HttpStatus.OK);
    }
}
