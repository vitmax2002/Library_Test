package library.service;

import library.model.Book;
import library.model.Publisher;
import library.repository.BookRepository;
import library.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository repository, PublisherRepository publisherRepository) {
        this.bookRepository = repository;
        this.publisherRepository = publisherRepository;
    }

    public Book createBook(BookSaveDto dto)
    {
        Book toSave = new Book();
        // setteri
        toSave.setIsbn(dto.isbn());
        toSave.setName(dto.name());
        Publisher publisher = publisherRepository.findById(dto.publisherId()).orElseThrow();
        toSave.setPublisher(publisher);
        toSave.setPublishYear(dto.publishYear());
        toSave.setCopies(dto.copies());
        toSave.setPicture(dto.picture());
        return bookRepository.save(toSave);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitatea nu a fost găsită."));
    }

    public Book updateBook(String id, BookSaveDto dto) {
        Book book = bookRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        book.setIsbn(dto.isbn());
        book.setName(dto.name());
        Publisher publisher = publisherRepository.findById(dto.publisherId()).orElseThrow();
        book.setPublisher(publisher);
        book.setPublishYear(dto.publishYear());
        book.setCopies(dto.copies());
        book.setPicture(dto.picture());
        return bookRepository.save(book);
    }



    public String delete(String id) {
        Book book2 = bookRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        String s=book2.getName();
        bookRepository.deleteById(id);
        return "Elementul cu numele "+ s+ " a fost sters cu succes";
    }
}
