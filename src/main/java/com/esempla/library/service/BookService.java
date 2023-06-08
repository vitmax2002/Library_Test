package com.esempla.library.service;


import com.esempla.library.model.Author;
import com.esempla.library.model.Book;
import com.esempla.library.service.dto.BookSaveDto;
import com.esempla.library.model.Publisher;
import com.esempla.library.repository.AuthorRepository;
import com.esempla.library.repository.BookRepository;
import com.esempla.library.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private Logger log= LoggerFactory.getLogger(BookService.class);
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(AuthorRepository authorRepository, BookRepository repository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = repository;
        this.publisherRepository = publisherRepository;
    }

    public Book createBook(BookSaveDto dto)
    {
        Book toSave = new Book();
        toSave.setIsbn(dto.isbn());
        toSave.setName(dto.name());
        Publisher publisher = publisherRepository.findById(dto.publisherId()).orElseThrow();
        toSave.setPublisher(publisher);
        toSave.setPublishYear(dto.publishYear());
        toSave.setCopies(dto.copies());
        toSave.setPicture(dto.picture());
        Set<Author> autori=new HashSet<>();
        if(dto.authors()!=null){
            for (var authorId : dto.authors()) {
                authorRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("Nu exista autor cu id:" + authorId));
            }
        for (var authorId : dto.authors()) {
            Set<Book> setBook=new HashSet<>();
            autori.add(authorRepository.findById(authorId).orElseThrow());
            Author autor=authorRepository.findById(authorId).orElseThrow();
            setBook=autor.getBooks();
            setBook.add(toSave);
            autor.setBooks(setBook);
            authorRepository.save(autor);
        }
        toSave.setAuthors(autori);
    }
        log.debug("Book {} was added",dto);
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
        Set<Author> autori=new HashSet<>();
        if(dto.authors()!=null) {
            for (var authorId : dto.authors()) {
                authorRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("Nu exista autor cu id:" + authorId));
            }
            for (var authorId : dto.authors()) {
                Set<Book> setBook=new HashSet<>();
                autori.add(authorRepository.findById(authorId).orElseThrow());
                Author autor=authorRepository.findById(authorId).orElseThrow();
                setBook=autor.getBooks();
                setBook.add(book);
                autor.setBooks(setBook);
                authorRepository.save(autor);
            }
            book.setAuthors(autori);
        }
        log.debug("Book with id {} was updated",id);
        return bookRepository.save(book);
    }



    public void delete(String id) {
        Book book2 = bookRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));

        for(var author: authorRepository.findAll())
        {
            if(author.getBooks().contains(book2))
            {
                author.setBooks(author.getBooks().stream().filter(book -> !book.getIsbn().equals(id)).collect(Collectors.toSet()));
            }
        }
        bookRepository.deleteById(id);
    }
}
