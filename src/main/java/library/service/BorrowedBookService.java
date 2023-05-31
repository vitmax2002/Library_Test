package library.service;

import library.model.Book;
import library.model.BorrowedBook;
import library.model.Client;
import library.repository.BookRepository;
import library.repository.BorrowedBookRepository;
import library.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BorrowedBookService {
    private final ClientRepository repositoryClient;
    private final BookRepository repositoryBook;
    private final BorrowedBookRepository borrowedBookRepository;


    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository, BookRepository repositoryBook, ClientRepository repositoryClient) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.repositoryBook = repositoryBook;
        this.repositoryClient = repositoryClient;
    }

    public BorrowedBook createBook(BorrowedBookDto book) {
        BorrowedBook borrowedBook = new BorrowedBook();
        Book book2 = repositoryBook.findById(book.book())
                .orElseThrow(() -> new NoSuchElementException("Nu exista carti cu acest id"));

        if (book2.getCopies() == 0) throw new NoSuchElementException("Nu mai sunt carti de acest tip");
        book2.setCopies(book2.getCopies() - 1);

         borrowedBook.setBook(book2);
        Client client = repositoryClient.findById(book.client())
                .orElseThrow(() -> new NoSuchElementException("Nu este client cu acest id"));
        borrowedBook.setClient(client);
        borrowedBook.setBorrowDate(book.borrowDate());
       repositoryBook.save(book2);


        return borrowedBookRepository.save(borrowedBook);
    }

    public List<BorrowedBook> getAll() {
        return borrowedBookRepository.findAll();
    }
/*
    public Book getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitatea nu a fost găsită."));
    }

    public Book updateBook(String id, BookSaveDto dto) {
        Book book = repository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        book.setIsbn(dto.isbn());
        book.setName(dto.name());
        Publisher publisher = publisherRepository.findById(dto.publisherId()).orElseThrow();
        book.setPublisher_id(publisher);
        book.setPublish_year(dto.publishYear());
        book.setCopies(dto.copies());
        book.setPicture(dto.picture());
        return repository.save(book);
    }
*/
    public String delete(int id) {
        BorrowedBook book2 = borrowedBookRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        book2.getBook().setCopies(book2.getBook().getCopies()+1);
        repositoryBook.save(book2.getBook());
        String s=book2.getBook().getName();
        int nr=book2.getBook().getCopies();
        borrowedBookRepository.deleteById(id);
        return "Cartea: "+s+" a fost eliberata/n"+
                "In stoc au ramas: "+nr;
    }

}
